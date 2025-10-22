package com.ums.h2sm.Mail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;


@Controller
public class MailController {
    private final MailFilter mailFilter;

    public MailController(MailFilter mailFilter) {
        this.mailFilter = mailFilter;
    }

    @PostMapping("/mail")
    @ResponseBody
    public void sendMail(@RequestBody MailContentDTO mailContentDTO) throws MessagingException {
        mailFilter.sendMailFilter(mailContentDTO);
    }
}
