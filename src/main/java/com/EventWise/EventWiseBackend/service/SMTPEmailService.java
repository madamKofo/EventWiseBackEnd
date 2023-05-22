package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.repository.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import java.util.Properties;

@Service
public class SMTPEmailService implements EmailService {

    private final Environment environment;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    public SMTPEmailService(Environment environment, JavaMailSender mailSender) {
        this.environment = environment;
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", environment.getProperty("spring.mail.host"));
        properties.put("mail.smtp.port", environment.getProperty("spring.mail.port"));
        properties.put("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));

        Session session = Session.getInstance(properties);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }
}
