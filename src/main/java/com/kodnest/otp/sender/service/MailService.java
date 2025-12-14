package com.kodnest.otp.sender.service;

import com.kodnest.otp.sender.entity.Mail;

public interface MailService {
	void sendMail(Mail mail);
	boolean validateOtp(String userOtp);
}
