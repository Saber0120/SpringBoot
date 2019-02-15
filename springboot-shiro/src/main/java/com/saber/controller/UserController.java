package com.saber.controller;

import com.github.pagehelper.PageInfo;
import com.saber.entity.User;
import com.saber.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/findUserByUsername")
    @ResponseBody
    public User findUserByUsername(@RequestParam("username") String username) {
        User user = userService.findUserByUsername(username);
        return user;
    }

    @RequestMapping("/userList")
    @RequiresPermissions("user:view")
    public String userList(int pageNum, int pageSize, Model model) {
        LOGGER.info("pageNum = {}, pageSize = {}", pageNum, pageSize);
        PageInfo<User> pageInfo = userService.listUser(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "user/userList";
    }

    @RequestMapping("/userAdd")
    @RequiresPermissions("user:add")
    public String userAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/userDel")
    @RequiresPermissions("user:del")
    public String userDel() {
        return "user/userDel";
    }
}
