package com.example.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderService {


    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(String toEmail, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("petchesi.adriana@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        log.info("sent email from ...");
        this.mailSender.send(message);
        System.out.println("Mail sent ..");
        log.info("email sent");
    }
}
