package com.dyy.controller;

import com.dyy.pojo.User;
import com.dyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/f",  method = RequestMethod.POST)
    @ResponseBody
    public User findUser(Integer userid)throws Exception{
        User user = userService.getUserByUid(userid);
        return user;
    }
}
