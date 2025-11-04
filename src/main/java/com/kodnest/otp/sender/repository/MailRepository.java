package com.kodnest.otp.sender.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.otp.sender.entity.Mail;

public interface MailRepository extends JpaRepository<Mail, Integer> {

}
