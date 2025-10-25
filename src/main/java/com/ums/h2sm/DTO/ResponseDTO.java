package com.ums.h2sm.DTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseDTO {
    private int status;
    private String message;
}
