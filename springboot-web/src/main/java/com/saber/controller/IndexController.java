package com.saber.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    // 访问 http://localhost:8888/saber/
    @RequestMapping("/")
    public String index() {
        return "Hi Saber";
    }
}
