package com.corpbay.application.api.services;

import com.corpbay.application.api.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    public boolean registrationMail(Users users) throws UnsupportedEncodingException {
        String subject = "Welcome to CorpBay! Verify you Email address";
        String link = "http://corpbay.herokuapp.com/users/verify?email=" + users.getEmail() + "&password="+ URLEncoder.encode(users.getPassword(),"UTF-8");
        String body = "<html>" +
                "<body>" +
                "<h1>Thank You " + users.getName() + "</h1>" +
                "<p style='font-size:1.5em'>You have been successfully registered to CorpBay. Click on the below link to verify</p><br>" +
                "<a href="+ link +" style='color:#fff;background-color:#28a745;border-color:#28a745;display:inline-block;font-weight:400;color:#212529;text-align:center;vertical-align:middle;cursor:pointer;'>" +
                "Link to verify</a>" +
                "</body>" +
                "<html>";
        return sendMail(users.getEmail(),subject,body);
    }

    public boolean sendMail(String to, String subject, String body) {
        try {
            MimeMessage mailMessage = mailSender.createMimeMessage();
            mailMessage.addRecipients(Message.RecipientType.TO, to);
            mailMessage.setSubject(subject);
            mailMessage.setContent(body,"text/html");
            mailSender.send(mailMessage);
            return true;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
