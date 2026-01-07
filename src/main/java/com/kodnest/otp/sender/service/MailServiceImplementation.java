package com.kodnest.otp.sender.service;

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

	 @Autowired private JavaMailSender sender;
	 @Autowired private MailRepository repo;

	    private String generatedOtp;

	    public void sendMail(Mail mail) {

	        generatedOtp = String.format("%06d", new Random().nextInt(999999));
	        mail.setOtp(generatedOtp);   // store OTP

	        MimeMessage msg = sender.createMimeMessage();
	        MimeMessageHelper mmh = new MimeMessageHelper(msg, "utf-8");

	        try {
	            mmh.setTo(mail.getReceiver());
	            mmh.setSubject("OTP");
	            mmh.setText("Use this OTP: " + generatedOtp);
	            repo.save(mail);
	            sender.send(msg);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }

	    public boolean validateOtp(String userOtp) {
	        return userOtp != null && userOtp.equals(generatedOtp);
	    }

}
