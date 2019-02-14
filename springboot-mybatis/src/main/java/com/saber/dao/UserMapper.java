package com.saber.dao;

import com.github.pagehelper.Page;
import com.saber.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    User findUserByUsername(String username);

    void updateUserByUsername(User user);

    void deleteUserByUsername(String username);

    void saveUser(User user);

    List<User> getUserList();

    @Select("SELECT * FROM USER")
    Page<User> getUserPage();
}
