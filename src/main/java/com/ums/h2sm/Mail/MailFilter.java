package com.ums.h2sm.Mail;

import com.ums.h2sm.DTO.ResponseDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Service
class MailFilter {
    @Value("${spring.data.password}")
    private String password;

    void sendMailFilter(MailContentDTO mailContentDTO) throws MessagingException {
        long before = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> {
            try {
                MailSender.sendMail(password,mailContentDTO);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });

        long after = System.currentTimeMillis();

        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(200)
                .message("메일 전송에 성공했습니다!\n메일이 도착하지 않았다면 다음을 확인해주세요\n\n에러사이트 명시")
                .build();

        //System.out.println("소요시간(비동기처리) : " + (after - before));

//        return ResponseEntity
//                .status(HttpStatus.OK)
//               .body(responseDTO);

        //MailSender.sendMail(password, mailContentDTO);
    }



    private static class MailSender {
        private static void sendMail(String password, MailContentDTO contents)
                throws MessagingException, AddressException {
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
            } catch (MessagingException e) {
                System.out.println("에러!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}