package com.spring_mail.MailSenderPart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring_mail.MailSenderPart.entity.MailDetails;
import com.spring_mail.MailSenderPart.service.mailService;

@RestController
public class HelloWordSenderMail {
	@Autowired
     private mailService mailservice;
     @PostMapping("/sendMail")
     public String sendsMail(@RequestBody MailDetails maildetails)
     {
    	 ///s dfa fd a
    	 if(mailservice.sentTo(maildetails)) 
      	 return "sent";
    	 return "not sent";
     }
}
