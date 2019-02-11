package com.saber;

import com.saber.interceptor.CommonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringbootInterceptorApplication implements WebMvcConfigurer {

    @Autowired
    private CommonInterceptor commonInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootInterceptorApplication.class, args);
    }

    /**
     * 增加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor);
    }
}

