package com.github.bruce_mig.java_mail_smtp.controller;

import com.github.bruce_mig.java_mail_smtp.dto.SendRequestDto;
import com.github.bruce_mig.java_mail_smtp.service.SendEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final SendEmailService sendEmailService;

    public EmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody SendRequestDto sendRequestDto) {
        sendEmailService.sendEmail(sendRequestDto.to(),
                                    sendRequestDto.subject(),
                                    sendRequestDto.body());

        return ResponseEntity.ok().body("Email sent successfully");
    }
}
