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


@Controller
public class MailController {
	
	@Autowired private MailService service;
	
	@GetMapping("/send-otp")
    public String sendOtp(@ModelAttribute Mail mail) {
        service.sendMail(mail);
        return "validate";   // page to enter OTP
    }

    // 🔹 Step 2: Validate OTP
    @PostMapping("/validate-otp")
    public String validateOtp(@RequestParam("otp") String otp, Model model) {

        boolean valid = service.validateOtp(otp);

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
