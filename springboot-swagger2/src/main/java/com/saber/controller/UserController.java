package com.saber.controller;

import com.saber.entity.User;
import com.saber.repository.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "用户操作接口", tags = {"用户操作接口"})
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "获取用户信息", notes = "通过id获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "int")
    @GetMapping("/findById")
    public User findById(@RequestParam(value = "id") int id) {
        return userRepository.findById(id).orElse(null);
    }

    @ApiOperation(value = "获取用户列表", notes = "获取全部用户信息")
    @GetMapping("/getUserList")
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "保存用户", notes = "保存用户信息")
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody @ApiParam(name = "用户对象", value = "传入json对象", required = true) User user) {
        userRepository.save(user);
        return "Success";
    }

    @ApiOperation(value = "修改用户", notes = "根据id修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("/updateUser")
    public String updateUser(@RequestParam(value = "id") int id, @RequestParam(value = "userName") String userName,
                             @RequestParam(value = "password") String password) {
        User user = new User(id, userName, password);
        userRepository.save(user);
        return "Success";
    }

    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "int")  // dataType和参数中的类型需要一致
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id") int id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.delete(user);
        return "Success";
    }
}
