package com.saber;

import com.saber.config.ShiroRedisCache;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private ShiroRedisCache redisCache;

    @Test
    public void testPut() {
        String k = "Saber";
        String v = "29";
        redisCache.put(k, v);
        System.out.println(redisCache.get(k));
        Assert.assertEquals(v, redisCache.get(k));
    }
}
