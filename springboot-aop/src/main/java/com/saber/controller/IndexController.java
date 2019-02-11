package com.saber.controller;

import com.saber.annotation.DoneTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/test1")
    @DoneTime(param = "IndexController")
    public String test1() {
        System.out.println("方法test1执行");
        return "doneTime";
    }

    @GetMapping("/test2")
    public String test2() {
        System.out.println("方法test2执行");
        return "execution";
    }
}
