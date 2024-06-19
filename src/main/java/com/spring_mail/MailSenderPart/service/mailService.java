package com.spring_mail.MailSenderPart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.spring_mail.MailSenderPart.entity.MailDetails;

import jakarta.transaction.Transactional;

@Service
public class mailService {
	@Autowired
	private JavaMailSender sender;
	 
     

	public boolean sentTo(MailDetails details)
      {
		  try {
			  SimpleMailMessage message =new SimpleMailMessage();
		     message.setTo(details.getToMail());
		     message.setSubject(details.getSubject());
		     message.setText(details.getBody());
		     message.setFrom(details.getFromMail());
		     sender.send(message);
		    
		     return true;
		  } 
		  catch(Exception e)
		  {
			  System.out.println("Problem "+e);
			  return false;
		  }
    	//  return false;
      }
}
