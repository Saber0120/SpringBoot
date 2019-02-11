package com.saber.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发生异常时，都在找'/error'这个页面，所以建一个controller实现ErrorController来统一处理异常
 */
@RestController
public class CommonErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH)
    public String handleError() {
        System.out.println(getErrorPath());
        return "error";
    }
}
