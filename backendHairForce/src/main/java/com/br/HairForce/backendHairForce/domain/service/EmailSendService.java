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

    public void sendSetPasswordEmail(String email) throws MessagingException{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("arthurpulsar8.6.1@gmail.com");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Set Password");
        mimeMessageHelper.setText("""
            <div>
                <a href="http://localhost:5173/setarSenha">Clique aqui para definir sua senha</a>
            </div>
            """.formatted(email), true);

        mailSender.send(mimeMessage);
    }

}
