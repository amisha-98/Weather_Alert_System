package com.example.weatheralert.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.weatheralert.model.WeatherData;
import org.json.JSONObject;

@Service
public class WeatherService {
    private final String API_KEY = "8194821e398624dcce8eb8cdd5efcac5";

    public boolean isSevereWeather(WeatherData data) {
        if (data.getTemperature() > 40) return true;
        if (data.getTemperature() < 5) return true;
        if (data.getRainfall() != null && data.getRainfall() > 50) return true;
        String condition = data.getCondition() != null ? data.getCondition().toLowerCase() : "";
        if (condition.contains("thunderstorm") || condition.contains("hail")) return true;
        return false;
    }

    public WeatherData getWeather(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                    "&appid=" + API_KEY + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);

        double temperature = json.getJSONObject("main").getDouble("temp");

        Double rainfall = null;
        if (json.has("rain") && json.getJSONObject("rain").has("1h")) {
            rainfall = json.getJSONObject("rain").getDouble("1h");
        }

        String condition = "";
        if (json.has("weather")) {
            condition = json.getJSONArray("weather").getJSONObject(0).getString("description");
        }

        String alertMessage = generateAlertMessage(city, temperature, rainfall, condition);

        return new WeatherData(city, temperature, alertMessage, rainfall, condition);
    }

    public WeatherData getWeatherByCoordinates(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat +
                    "&lon=" + lon + "&appid=" + API_KEY + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);

        String city = json.getString("name");
        double temperature = json.getJSONObject("main").getDouble("temp");

        Double rainfall = null;
        if (json.has("rain") && json.getJSONObject("rain").has("1h")) {
            rainfall = json.getJSONObject("rain").getDouble("1h");
        }

        String condition = "";
        if (json.has("weather")) {
            condition = json.getJSONArray("weather").getJSONObject(0).getString("description");
        }

        String alertMessage = generateAlertMessage(city, temperature, rainfall, condition);

        return new WeatherData(city, temperature, alertMessage, rainfall, condition);
    }

    private String generateAlertMessage(String city, double temperature, Double rainfall, String condition) {
        String cond = condition != null ? condition.toLowerCase() : "";

        if (temperature > 40) return "Extreme high temperature alert!";
        if (temperature < 5) return "Extreme low temperature alert!";
        if ((cond.contains("thunderstorm") || cond.contains("lightning")) && rainfall != null && rainfall > 50) {
            return "Thunderstorm/Lightning with Heavy Rain is very likely to occur at " + city + ".";
        }
        if (cond.contains("thunderstorm") || cond.contains("lightning")) {
            return "Thunderstorm/Lightning is very likely to occur at " + city + ".";
        }
        if (rainfall != null && rainfall > 50) {
            return "Heavy rainfall alert in " + city + ".";
        }
        if (cond.contains("hail")) {
            return "Hailstorm is very likely to occur at " + city + ".";
        }

        return "No alerts at this time.";
    }
}
