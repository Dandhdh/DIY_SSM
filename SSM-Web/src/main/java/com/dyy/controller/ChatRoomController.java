package com.dyy.controller;

import com.dyy.pojo.Content;
import com.dyy.service.ContentService;
import com.dyy.util.CommonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/")
public class ChatRoomController {

    @Autowired
    private ContentService contentService;


    @RequestMapping("/turnToWebSocketIndex")
    public String turnToWebSocketIndex(){
        return  "websocket/websocket" ;
    }

    /**
     * 加载聊天记录
     * @param response
     */
    @RequestMapping("/content_load")
    public void content_load(HttpServletResponse response){
        JSONObject jsonObject = new JSONObject() ;
        try {
            JSONObject jo = new JSONObject() ;
            List<Content> list = contentService.findContentList();
            jo.put("contents",list) ;
            jsonObject = CommonUtil.parseJson("1","操作成功",jo);
        }catch (Exception e){
            e.printStackTrace();
            CommonUtil.parseJson("2","操作异常","");
        }
        CommonUtil.responseBuildJson(response,jsonObject);
    }
}
