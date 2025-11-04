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

	@Autowired
	MailRepository repo;
	
	@Autowired
	JavaMailSender sender;
	public void sendMail(Mail mail) {
		// TODO Auto-generated method stub
		String otp = String.format("%06d", new Random().nextInt(999999));
		
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(msg,"utf-8");
		
		try {
			mmh.setTo(mail.getReceiver());
			mmh.setSubject("OTP");
			mmh.setText("use this OTP: " + otp);
			sender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
