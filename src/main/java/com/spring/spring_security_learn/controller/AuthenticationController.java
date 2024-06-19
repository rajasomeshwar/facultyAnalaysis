package com.spring.spring_security_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_security_learn.model.ApplicationUser;
import com.spring.spring_security_learn.model.LoginResponseDTO;
import com.spring.spring_security_learn.model.RegistrationDTO;
import com.spring.spring_security_learn.service.AuthenticationService;

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
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
    	
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
    
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}   