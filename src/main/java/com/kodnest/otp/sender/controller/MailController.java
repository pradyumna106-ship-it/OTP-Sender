package com.kodnest.otp.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.kodnest.otp.sender.entity.Mail;
import com.kodnest.otp.sender.service.MailService;

@Controller
public class MailController {
	
	@Autowired
	MailService service;
	
	@GetMapping("/send")
	public String send(@ModelAttribute Mail mail) {
		service.sendMail(mail);
		return "validate";
		
	}
}
