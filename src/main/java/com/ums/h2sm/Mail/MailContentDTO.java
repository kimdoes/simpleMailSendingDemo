package com.ums.h2sm.Mail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class MailContentDTO {
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @Email
    private String emailAddress;
}
