package com.saber.service;

import com.saber.entity.SysUser;

public interface UserService {

    SysUser getUserByName(String username);
}
