package com.saber.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.saber.dao.UserMapper;
import com.saber.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_MAX_PAGE_SIZE = 100;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        LOGGER.info("findUserByUsername");
        return userMapper.findUserByUsername(username);
    }

    @Override
    public PageInfo<User> listUser(int pageNum, int pageSize) {
        if (pageSize < 0) {
            LOGGER.error("pageSize = {}，不合理，默认赋值为：{}", pageSize, DEFAULT_PAGE_SIZE);
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageNum > DEFAULT_MAX_PAGE_SIZE) {
            LOGGER.error("pageNum = {}，值太大，赋值为最大值：{}", pageNum, DEFAULT_MAX_PAGE_SIZE);
            pageNum = DEFAULT_MAX_PAGE_SIZE;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.listUser();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }
}
