package com.klb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String fromAdress, String toAddress, String subject, String body) {
        //obiekt maila
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toAddress);
        message.setFrom(fromAdress);
        message.setSubject("[Biblioteka] " + subject);
        message.setText(body);

        mailSender.send(message);
    }
}
