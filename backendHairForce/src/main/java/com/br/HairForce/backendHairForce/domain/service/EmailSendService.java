package com.br.HairForce.backendHairForce.domain.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSetPasswordEmail(String email, String token) throws MessagingException{
        String resetLink = "http://localhost:5173/set-password?token=" + token;
        String message = "Clique no link abaixo para resetar sua senha:\n" + resetLink;

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("arthurpulsar8.6.1@gmail.com");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Set Password");
        mimeMessageHelper.setText(message);

        mailSender.send(mimeMessage);
    }

}
