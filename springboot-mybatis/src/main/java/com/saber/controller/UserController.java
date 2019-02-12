package com.saber.controller;

import com.saber.dao.UserMapper;
import com.saber.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUser")
    public String getUser(String username) {
        User user = userMapper.findUserByUsername(username);
        return user != null ? username + "的密码是：" + user.getUser_password() : "不存在" + username + "的用户";
    }

    @RequestMapping("/updateUser")
    public String updateUser(String password, String username) {
        User user = new User(username, password);
        userMapper.updateUserByUsername(user);
        return "Success";
    }

    @RequestMapping("/addUser")
    public String addUser(String username, String password) {
        User user = new User(username, password);
        userMapper.saveUser(user);
        return "Success";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(String username) {
        userMapper.deleteUserByUsername(username);
        return "Success";
    }

    @RequestMapping("/getUserList")
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}
