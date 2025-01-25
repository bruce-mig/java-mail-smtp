package com.github.bruce_mig.java_mail_smtp.service;

public interface SendEmailService {

    void sendEmail(String to, String subject, String content);

}
