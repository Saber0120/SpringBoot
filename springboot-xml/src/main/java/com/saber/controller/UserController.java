package com.saber.controller;

import com.saber.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public User index() {
        User user = new User("Saber", "29", "shen liang hua yuan");
        return user;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public User xml() {
        User user = new User("Saber", "30", "qin hui yuan");
        return user;
    }
}
