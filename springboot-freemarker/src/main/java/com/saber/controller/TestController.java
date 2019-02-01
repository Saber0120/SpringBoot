package com.saber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    // 访问 http://localhost:8888/test
    @RequestMapping("/test")
    public String testFreeMarker(ModelMap modelMap) {
        modelMap.addAttribute("msg", "Hello Saber, this is freemarker");
        return "freemarker";
    }
}
