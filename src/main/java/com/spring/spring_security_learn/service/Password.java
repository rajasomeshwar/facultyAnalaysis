package com.spring.spring_security_learn.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Password {
	private PasswordEncoder passwordEncodeer;
   public String passwordEncoder(String stn)
   {
	   return passwordEncodeer.encode(stn);
   }
}
