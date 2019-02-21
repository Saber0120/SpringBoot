package com.saber.controller;

import com.saber.entity.SysUser;
import com.saber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('UserIndex')")
    @RequestMapping("/index")
    public String index() {
        return "user/index";
    }

    @RequestMapping("/hi")
    @ResponseBody
    public String hi() {
        SysUser sysUser = userService.getUserByName("zhangsan");
        return sysUser.toString();
    }
}
