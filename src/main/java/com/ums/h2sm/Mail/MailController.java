package com.ums.h2sm.Mail;

import com.ums.h2sm.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<ResponseDTO> sendMail(@RequestBody MailContentDTO mailContentDTO) throws MessagingException {
        return mailFilter.sendMailFilter(mailContentDTO);
    }
}
