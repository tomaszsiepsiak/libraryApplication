package com.klb.service;


public interface EmailService {
    void sendEmail(String fromAdress, String toAddress, String subject, String body);
}
