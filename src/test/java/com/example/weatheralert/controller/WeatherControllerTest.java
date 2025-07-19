package com.example.weatheralert.controller;

import com.example.weatheralert.model.WeatherData;
import com.example.weatheralert.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void testWeatherPage() throws Exception {
        WeatherData mockData = new WeatherData("Delhi", 35, "No alerts at this time.", null, null);
        Mockito.when(weatherService.getWeather("Delhi")).thenReturn(mockData);

        mockMvc.perform(get("/weather"))
               .andExpect(status().isOk())
               .andExpect(view().name("weather"))
               .andExpect(model().attributeExists("temperature"))
               .andExpect(model().attributeExists("alertMessage"));
    }
}
