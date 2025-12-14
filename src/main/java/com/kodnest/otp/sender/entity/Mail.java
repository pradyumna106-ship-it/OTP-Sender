package com.kodnest.otp.sender.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String receiver;
    private String subject;
    private String body;

    // ✅ Add OTP field
    private String otp;

    public Mail() {
        super();
    }

    public Mail(int id, String receiver, String subject, String body, String otp) {
        super();
        this.id = id;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
        this.otp = otp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // ✅ OTP Getter & Setter
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "Mail [id=" + id + ", receiver=" + receiver +
               ", subject=" + subject + ", body=" + body +
               ", otp=" + otp + "]";
    }
}

