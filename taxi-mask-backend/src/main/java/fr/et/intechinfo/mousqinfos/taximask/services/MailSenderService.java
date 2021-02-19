package fr.et.intechinfo.mousqinfos.taximask.services;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String sendTo, String subject, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(sendTo);

        msg.setSubject(subject);

      //  VelocityEngine.VM_LIBRARY("com/dns/registration-confirmation.vm",msg);

        msg.setText(message);

        javaMailSender.send(msg);

    }

    public void sendEmailWithAttachment(String sendTo, String subject, String message, String filePath) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(sendTo);

        helper.setSubject(subject);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText(message, true);

        helper.addAttachment(filePath, new ClassPathResource(filePath));

        javaMailSender.send(msg);

    }
}
