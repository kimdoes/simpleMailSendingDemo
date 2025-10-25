package com.ums.h2sm.Mail;

import com.ums.h2sm.DTO.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Controller
public class MailController {
    private final MailFilter mailFilter;

    public MailController(MailFilter mailFilter) {
        this.mailFilter = mailFilter;
    }

    @GetMapping
    public String home(Model model) {
        return "mail";
    }

    @PostMapping("/mail")
    public String sendMail(@ModelAttribute MailContentDTO mailContentDTO, Model model) throws MessagingException {
        mailFilter.sendMailFilter(mailContentDTO);
        return "sending";
    }
}