package com.example.weatheralert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AlertMailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendWeatherAlert(String to, String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("test@myapp.local"); // For Mailtrap, any fake domain is fine
        mail.setTo("your-gmail-account@gmail.com");
        mail.setSubject(subject);
        mail.setText(body);
        mailSender.send(mail);
    }
}
