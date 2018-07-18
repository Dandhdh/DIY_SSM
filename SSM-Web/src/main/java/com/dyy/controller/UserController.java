package com.dyy.controller;

import com.dyy.lucene.Indexer;
import com.dyy.lucene.Searcher;
import com.dyy.pojo.User;
import com.dyy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/frontUserSet/{id}")
    public String frontUserSet(@PathVariable int id, Model model) {
        /*User user = userService.getUserById(id);
        model.addAttribute("currentUser", user);
        String img_id = user.getImg_id();
        if (img_id != null) {
            String path = imgService.selectByPrimaryKey(Integer.parseInt(img_id)).getPath();
            model.addAttribute("headimg", path);
        }*/
        return "front/user/userSet";
    }

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }


    @RequestMapping("/showUser/{id}")
    public String showUser(@PathVariable int id, HttpServletRequest request) {
        User u = userService.getUserById(id);
        request.setAttribute("user", u);
        return "showUser";
    }

    @RequestMapping("/turnToIndex")
    public String turnToIndex() {
        return "index";
    }

    @RequestMapping("/turnToUserList")
    public String turnToUserList() {
        return "user/userList";
    }


    @RequestMapping("/queryUser")
    public String queryUser(String q) throws Exception {
        q = new String(q.getBytes("ISO-8859-1"), "UTF-8");
        User u = new User() ;
        List<User> users = userService.findAllByQuery(u) ;
        List<Integer> ids = new ArrayList<Integer>();
        List<String> city = new ArrayList<String>() ;
        List<String> descs = new ArrayList<String>() ;
        for (User user : users){
            ids.add(user.getUserId()) ;
            city.add(user.getUsername()) ;
            descs.add(user.getDescription()) ;
        }
        Indexer indexer = new Indexer() ;
        Integer[] id = (Integer[])ids.toArray(new Integer[ids.size()]);
        String[] citys = (String[])city.toArray(new String[city.size()]);
        String[] desc = (String[])descs.toArray(new String[descs.size()]);
        indexer.setIds(id);
        indexer.setCitys(citys);
        indexer.setDescs(desc);
        indexer.index("E:\\Lucene");
        String indexDir="E:\\Lucene";
        try {
            Searcher.search(indexDir,q);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null ;
    }

    @RequestMapping("/getUserList")
    public void getUserList(@ModelAttribute User user, HttpServletResponse response, int page, int rows) {
        /*response.setCharacterEncoding("utf-8");
        Page<User> users = userService.findByParams(user,page,rows) ;
        for(User u :users.getRows()){
            //将角色ID转换为角色名称
            String role_id = u.getRole_id() ;
            if(role_id != null){
                Role role = roleService.selectByPrimaryKey(Integer.parseInt(role_id)) ;
                u.setRolename(role.getRole_name()) ;
            }
            Date date = u.getLast_date() ;
            if(date != null){
                SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
                String strDate = sm.format(date) ;
                u.setParsedate(strDate) ;
            }
        }

        String json = JSON.toJSONString(users) ;
        try {
            response.getWriter().print(json) ;
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    /**
     * @param @param user
     * @param @param request
     * @return void
     * @throws IOException
     * @throws
     * @Description: 新建用户
     * @author chj
     * @date 2016-1-2  下午11:06:27
     */
    @RequestMapping("/create")
    public void createUser(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            userService.createUser(user);
            response.getWriter().print("true");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("false");
        }
    }

    @RequestMapping("/delete")
    public void delete(String ids, HttpServletResponse response) throws IOException {
        String[] str_ids = ids.split(",");
        for (String id : str_ids) {
            userService.deleteByPrimaryKey(Integer.parseInt(id));
            response.getWriter().print("true");
        }
    }

    @RequestMapping("/edit")
    public void edit(User user, HttpServletResponse response) {
        try {
            userService.updateByPrimaryKeySelective(user);
            response.getWriter().print("true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:../login.jsp";
    }


}
