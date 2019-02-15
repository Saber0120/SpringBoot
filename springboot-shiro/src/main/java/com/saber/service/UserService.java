package com.saber.service;

import com.github.pagehelper.PageInfo;
import com.saber.entity.User;

public interface UserService {

    User findUserByUsername(String username);

    PageInfo<User> listUser(int pageNum, int pageSize);
}
