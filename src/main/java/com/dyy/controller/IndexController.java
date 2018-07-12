package com.dyy.controller;

import com.dyy.lucene.LuceneIndex;
import com.dyy.pojo.PageEntity;
import com.dyy.pojo.User;
import com.dyy.service.UserService;
import com.dyy.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {
    @Resource
    private UserService userService;

    /** 首页 */
    @RequestMapping("/index")
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        HttpServletRequest request, Model model) {
        PageEntity pageEntity = new PageEntity(page, 10);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageEntity.getStart());
        map.put("size", pageEntity.getPageSize());

        List<User> users = userService.list(map);
        Long total = userService.getTotal(map);
        model.addAttribute("users", users);

        // 拼凑成 分页的html
        StringBuffer param = new StringBuffer();
        String pageHtml = PageUtil.genPagination(request.getContextPath() + "/index", total, page, 10, param.toString());

        model.addAttribute("pageHtml", pageHtml);
        return "/index";
    }

    /**
     * 全文索引查询
     *
     * @param q    查询字段
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/q")
    public String search(@RequestParam(value = "q", required = false,defaultValue = "") String q,
                         @RequestParam(value = "page", required = false, defaultValue = "1") String page,
                         Model model,
                         HttpServletRequest request) throws Exception {
        LuceneIndex luceneIndex = new LuceneIndex() ;
        List<User> userList = luceneIndex.searchBlog(q);
        Integer toIndex = userList.size() >= Integer.parseInt(page) * 5 ? Integer.parseInt(page) * 5 : userList.size();
        List<User> newList = userList.subList((Integer.parseInt(page) - 1) * 5, toIndex);
        model.addAttribute("userList",newList) ;
        String s = this.genUpAndDownPageCode(Integer.parseInt(page), userList.size(), q, 5,
                request.getServletContext().getContextPath());
        model.addAttribute("pageHtml",s) ;
        model.addAttribute("q",q) ;
        model.addAttribute("resultTotal",userList.size()) ;
        model.addAttribute("pageTitle","搜索关键字'" + q + "'结果页面") ;

        return "queryResult";
    }

    /**
     * 重新生成全文索引
     */
    @RequestMapping("/createAllIndex")
    public void createAllIndex(HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> users = userService.list(map);
        //先删除原有的索引再创建新的
        for (User user : users) {
            // 删除原有索引
            LuceneIndex luceneIndex = new LuceneIndex();
            luceneIndex.deleteIndex(user.getUserId() + "");
        }
        for (User user : users) {
            // 新建索引
            LuceneIndex luceneIndex = new LuceneIndex();
            luceneIndex.addIndex(user);
        }
        response.getWriter().print("true");
    }


    /**
     * 查询之后的分页
     * @param page
     * @param totalNum
     * @param q
     * @param pageSize
     * @param projectContext
     * @return
     */
    private String genUpAndDownPageCode(int page,Integer totalNum,String q,Integer pageSize,String projectContext){
        long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode=new StringBuffer();
        if(totalPage==0){
            return "";
        }else{
            pageCode.append("<nav>");
            pageCode.append("<ul class='pager' >");
            if(page>1){
                pageCode.append("<li><a href='"+projectContext+"/q?page="+(page-1)+"&q="+q+"'>上一页</a></li>");
            }else{
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }
            if(page<totalPage){
                pageCode.append("<li><a href='"+projectContext+"/q?page="+(page+1)+"&q="+q+"'>下一页</a></li>");
            }else{
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            pageCode.append("</ul>");
            pageCode.append("</nav>");
        }
        return pageCode.toString();
    }
}
