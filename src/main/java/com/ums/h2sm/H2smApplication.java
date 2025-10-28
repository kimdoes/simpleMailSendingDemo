package com.ums.h2sm;

import com.ums.h2sm.Mail.ThreadTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Slf4j
@SpringBootApplication
public class H2smApplication {
	public static void main(String[] args) {
        log.info("애플리케이션 시작됨.");
		SpringApplication.run(H2smApplication.class, args);
	}

}
