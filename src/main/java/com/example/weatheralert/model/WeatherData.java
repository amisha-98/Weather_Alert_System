package com.example.weatheralert.model;

public class WeatherData {

    private String city;
    private double temperature;
    private String alertMessage;
    private Double rainfall;    // Nullable
    private String condition;   // Weather condition description

    // Constructor
    public WeatherData(String city, double temperature, String alertMessage, Double rainfall, String condition) {
        this.city = city;
        this.temperature = temperature;
        this.alertMessage = alertMessage;
        this.rainfall = rainfall;
        this.condition = condition;
    }

    // Getters
    public String getCity() { return city; }
    public double getTemperature() { return temperature; }
    public String getAlertMessage() { return alertMessage; }
    public Double getRainfall() { return rainfall; }
    public String getCondition() { return condition; }
}