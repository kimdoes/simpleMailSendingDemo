package com.ums.h2sm.Mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Controller
class MailSender {
    @Value("${spring.data.password}")
    private String password;

    void sendMail(MailContentDTO contents) throws MessagingException, AddressException {
        System.out.println("시작");
        long before = System.currentTimeMillis();
        String user = "hongchelin422@gmail.com";

        if (password == null) {
            throw new NullPointerException();
        }

        String title = contents.getTitle();
        String content = contents.getContent();
        String address = contents.getEmailAddress();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress((address)));
            message.setSubject(title);
            message.setText(content);

            Transport.send(message);
            long after = System.currentTimeMillis();
            System.out.println("소요시간 : " + (-before + after));
        } catch (MessagingException  e) {
            e.printStackTrace();
        }

    }
}
