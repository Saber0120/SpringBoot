package com.saber.controller;

import com.saber.entity.Demo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DemoTestController {

    @PostMapping("/")
    public String testDemo(@Valid Demo demo, BindingResult bindingResult) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bindingResult.hasErrors()) {
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError objectError : list) {
                stringBuffer.append(objectError.getDefaultMessage());
                stringBuffer.append("---");
            }
        }
        return stringBuffer != null ? stringBuffer.toString() : "";
    }
}
