package com.saber;

import com.saber.entity.User;
import com.saber.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootShiroApplication.class)
public class MybatisTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindUserByUsername() {
        User user = userService.findUserByUsername("zhangsan");
        Assert.assertEquals(user.getDescription(), "张三");
    }
}
