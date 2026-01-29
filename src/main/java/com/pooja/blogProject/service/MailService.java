package com.pooja.blogProject.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtpMail(String toEmail, String otp) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Password Reset OTP");
            helper.setText(
                    "<h3>Password Reset</h3>" +
                            "<p>Your OTP is: <b>" + otp + "</b></p>" +
                            "<p>Valid for 5 minutes.</p>",
                    true
            );
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace(); // 🔥 SEE REAL ERROR
            throw new RuntimeException("Failed to send email", e);
        }
    }
}