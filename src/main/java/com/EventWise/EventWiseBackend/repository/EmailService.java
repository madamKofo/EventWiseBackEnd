package com.EventWise.EventWiseBackend.repository;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
