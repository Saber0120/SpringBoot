package com.saber.controller;

import com.saber.entity.primary.City;
import com.saber.entity.secondary.House;
import com.saber.repository.primary.CityRepository;
import com.saber.repository.secondary.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private HouseRepository houseRepository;

    @GetMapping("/primary")
    public String testPrimary() {
        City city = new City("南通", "位于江苏省");
        cityRepository.save(city);
        return "success";
    }

    @GetMapping("/secondary")
    public String testSecondary() {
        House house = new House("豪宅", "特别大");
        houseRepository.save(house);
        return "Success";
    }
}
