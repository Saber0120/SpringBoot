package com.saber.controller;

import com.saber.entity.House;
import com.saber.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseController {

    @Autowired
    private HouseRepository houseRepository;

    @GetMapping("/test1")
    public String test1() {
        houseRepository.save(new House("house1", "100平"));
        houseRepository.save(new House("house2", "100平"));
        houseRepository.save(new House("house3", "100平"));
        houseRepository.save(new House("house444444444444", "100平"));
        houseRepository.save(new House("house5", "100平"));
        return "success";
    }

    @GetMapping("/test2")
    @Transactional
    public String test2() {
        houseRepository.save(new House("house6", "110平"));
        houseRepository.save(new House("house7", "110平"));
        houseRepository.save(new House("house8", "110平"));
        houseRepository.save(new House("house9999999999", "110平"));
        houseRepository.save(new House("house10", "110平"));
        return "success";
    }
}
