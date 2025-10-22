package com.ums.h2sm.Mail;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@EnableAsync
class MailFilter {
    private final MailSender mailSender;

    private MailFilter(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    void sendMailFilter(MailContentDTO mailContentDTO) throws MessagingException {
        mailSender.sendMail(mailContentDTO);
    }

}
