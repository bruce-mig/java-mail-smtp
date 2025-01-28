package com.github.bruce_mig.java_mail_smtp.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService{

    Logger log = LoggerFactory.getLogger(SendEmailServiceImpl.class);

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    public SendEmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String content) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);

            FileSystemResource file = new FileSystemResource("go.png");
            FileSystemResource pdfFile = new FileSystemResource("dummy.pdf");

            helper.addAttachment(file.getFilename(), file);
            helper.addAttachment(pdfFile.getFilename(), pdfFile);

            javaMailSender.send(mimeMessage);

            log.info("Email sent successfully to {}", to);
        } catch (MessagingException e) {
            log.error("Failed to send email: {}", e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
