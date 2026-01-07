package com.kodnest.otp.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.otp.sender.entity.Mail;
import com.kodnest.otp.sender.service.MailService;

import jakarta.servlet.http.HttpSession;


@Controller
public class MailController {
	
	@Autowired private MailService service;
	
	@GetMapping("/send-otp")
	public String sendOtp(@ModelAttribute Mail mail, HttpSession session) {

	    service.sendMail(mail);

	    // store email in session (safe for Railway)
	    session.setAttribute("otpEmail", mail.getReceiver());

	    return "validate"; // OTP input page
	}

    // 🔹 Step 2: Validate OTP
	@PostMapping("/validate-otp")
	public String validateOtp(
	        @RequestParam("otp") String otp,
	        HttpSession session,
	        Model model) {

	    String email = (String) session.getAttribute("otpEmail");

	    if (email == null) {
	        model.addAttribute("status", "Session expired. Please resend OTP.");
	        model.addAttribute("color", "red");
	        return "status";
	    }

	    boolean valid = service.validateOtp(email, otp);

	    if (valid) {
	        model.addAttribute("status", "Valid OTP");
	        model.addAttribute("color", "green");
	    } else {
	        model.addAttribute("status", "Invalid OTP");
	        model.addAttribute("color", "red");
	    }

	    return "status";
	}


}
