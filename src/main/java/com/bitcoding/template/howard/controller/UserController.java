package com.bitcoding.template.howard.controller;

import com.bitcoding.template.howard.entity.shiro.User;
import com.bitcoding.template.howard.service.ServiceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public User user(){
        return userService.getUserByUserName("liumeng");
    }

}
