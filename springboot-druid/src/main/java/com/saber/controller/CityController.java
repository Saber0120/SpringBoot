package com.saber.controller;

import com.saber.entity.City;
import com.saber.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/getById")
    public City getById(@RequestParam (value = "id") int id) {
        return cityRepository.findById(id).orElse(null);
    }

    @GetMapping("/saveCity")
    public String saveCity(@RequestParam ("cityName") String cityName,
                           @RequestParam ("cityIntroduce") String cityIntroduce) {
        City city = new City(cityName, cityIntroduce);
        cityRepository.save(city);
        return "Success";
    }

    @GetMapping("/updateCity")
    public String updateCity(@RequestParam ("id") int id,
                             @RequestParam ("cityName") String cityName,
                             @RequestParam ("cityIntroduce") String cityIntroduce) {
        City city = new City(id, cityName, cityIntroduce);
        cityRepository.save(city);
        return "Success";
    }

    @GetMapping("/deleteCity")
    public String deleteCity(@RequestParam ("id") int id) {
        City city = cityRepository.findById(id).orElse(null);
        if (city != null) {
            cityRepository.delete(city);
        }
        return "Success";
    }
}
