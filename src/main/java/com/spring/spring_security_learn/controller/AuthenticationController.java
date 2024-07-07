package com.spring.spring_security_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_security_learn.model.ApplicationUser;
import com.spring.spring_security_learn.model.LoginDTO;
import com.spring.spring_security_learn.model.LoginResponseDTO;
import com.spring.spring_security_learn.model.RegistrationDTO;
import com.spring.spring_security_learn.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")

@CrossOrigin("*")
public class AuthenticationController {

  
  @Autowired
	public AuthenticationController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	private AuthenticationService authenticationService;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationDTO body){
    	System.out.println(body);
        return authenticationService.registerUser(body.getUsername(), body.getPassword(),body.getMoblienumber(),body.getData());
    }
    
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginDTO body){
    	
        return authenticationService.loginUser(body.getEmail(), body.getPassword());
    }
}   