package com.kodnest.otp.sender.service;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kodnest.otp.sender.entity.Mail;
import com.kodnest.otp.sender.repository.MailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceImplementation implements MailService {

	@Autowired
    private JavaMailSender sender;

    @Autowired
    private MailRepository repo;

	@Override
	public void sendMail(Mail mail) {
		// TODO Auto-generated method stub
		String otp = String.format("%06d", new SecureRandom().nextInt(1_000_000));

	    mail.setOtp(otp);
	    mail.setSubject("OTP Verification");
	    mail.setBody("Use this OTP: " + otp);

	    try {
	        MimeMessage message = sender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	        helper.setTo(mail.getReceiver());
	        helper.setSubject(mail.getSubject());
	        helper.setText(mail.getBody(), false);

	        // Send email FIRST
	        sender.send(message);

	        // Save ONLY after success
	        repo.save(mail);

	    } catch (Exception e) {
	        throw new RuntimeException("Mail sending failed", e);
	    }
	}
	@Override
	public boolean validateOtp(String email, String userOtp) {
		// TODO Auto-generated method stub
		return repo.findTopByReceiverOrderByIdDesc(email)
	            .map(mail -> mail.getOtp().equals(userOtp))
	            .orElse(false);
	}
}
