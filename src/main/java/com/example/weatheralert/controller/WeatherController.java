package com.example.weatheralert.controller;

import com.example.weatheralert.model.WeatherData;
import com.example.weatheralert.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String weather(
        @RequestParam(required = false) String city,
        @RequestParam(required = false) Double lat,
        @RequestParam(required = false) Double lon,
        Model model
    ) {
        if (city == null || city.isEmpty()) city = "Delhi";

        WeatherData data;
        if (lat != null && lon != null) {
            data = weatherService.getWeatherByCoordinates(lat, lon);
        } else {
            data = weatherService.getWeather(city);
        }

        model.addAttribute("temperature", data.getTemperature());
        model.addAttribute("alertMessage", data.getAlertMessage()); // Always show this

        return "weather";
    }
}
