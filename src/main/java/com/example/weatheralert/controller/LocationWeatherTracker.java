package com.example.weatheralert.controller;

import com.example.weatheralert.model.WeatherData;
import com.example.weatheralert.service.AlertMailService;
import com.example.weatheralert.service.AlertSmsService;
import com.example.weatheralert.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class LocationWeatherTracker {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private AlertSmsService alertSmsService;

    @Autowired
    private AlertMailService alertMailService;

    @PostMapping("/track")
    public void trackUserWeather(@RequestBody Map<String, Double> locationData) {
        Double lat = locationData.get("lat");
        Double lon = locationData.get("lon");

        WeatherData data = weatherService.getWeatherByCoordinates(lat, lon);
        if (weatherService.isSevereWeather(data)) {
            String message = "🚨 Weather Alert: " + data.getAlertMessage()
                    + " Temp: " + data.getTemperature() + "°C";

            alertMailService.sendWeatherAlert(
                "your-gmail-account@gmail.com", // update as needed
                "Severe Weather Alert",
                message
            );
            alertSmsService.sendWeatherAlertSms(
                "your-phone-number", // update as needed
                message
            );
        }
        // No alert sent if weather is normal
    }
}
