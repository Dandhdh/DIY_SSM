package com.dyy.controller;

import com.dyy.pojo.T_user;
import com.dyy.service.T_userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * 后台Controller
 */
@Controller
@RequestMapping("/")
public class T_userController {

    @Autowired
    private T_userService t_userService ;

    @RequestMapping("/loginAdmin")
    public String login(T_user user, Model model, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword()) ;
        try {
            subject.login(token);
            return "admin" ;
        }catch (Exception e){
            //这里将异常打印关闭是因为如果登录失败的话会自动抛异常
//            e.printStackTrace();
            model.addAttribute("error","用户名或密码错误") ;
            // 当前目录在 /webapp/WEB-IN/jsp 需要 ../../ 返回到/webapp 下
            return "../../login" ;
        }
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/student")
    public String student(){
        return "result" ;
    }

    @RequestMapping("/teacher")
    public String teacher(){
        return "result" ;
    }
}
