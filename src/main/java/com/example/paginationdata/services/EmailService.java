package com.example.paginationdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(List<String> recipients, String subject, String message) throws MailException {
        for (String recipient : recipients) {
            try {
                MimeMessage mimeMessage = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setTo(recipient);
                helper.setSubject(subject);
                helper.setText(message, true);  // Используем HTML в письме
                emailSender.send(mimeMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
