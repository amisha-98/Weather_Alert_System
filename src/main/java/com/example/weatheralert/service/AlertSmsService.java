package com.example.weatheralert.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlertSmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    public void sendWeatherAlertSms(String to, String message) {
        try {
            Message.creator(
                new com.twilio.type.PhoneNumber("your-phone-number"),
                new com.twilio.type.PhoneNumber("your-twilio-number"),
                message
            ).create();
        } catch (Exception e) {
            // Log error or handle accordingly
        }
    }
}

