package com.spring.spring_security_learn.service;

import java.util.HashSet;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.spring_security_learn.exception.UserNotFoundException;
import com.spring.spring_security_learn.exception.UserNotVerfitedException;
import com.spring.spring_security_learn.model.*;
import com.spring.spring_security_learn.repository.RoleRepository;
import com.spring.spring_security_learn.repository.UserRepository;


@Service
@Transactional
public class AuthenticationService {

  
    private final UserRepository userRepository;

  
    private final RoleRepository roleRepository;

  
    private PasswordEncoder passwordEncoder;

 
    private AuthenticationManager authenticationManager;

  
    private TokenService tokenService;
@Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	public ApplicationUser registerUser(String username, String password){
       
    	// check if exists;
    	System.out.println("her ");
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new ApplicationUser(0, username, encodedPassword, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password){

        try{
        	System.out.println(" her login serivce");
        	var user=userRepository.findByUsergmail(username).orElse(null);
        	System.out.println(" her login serivce"+user);
        	if(user==null)
        		 throw new UserNotFoundException("Email does exists! ");
        	System.out.println("x " +user);
        	if(!user.getEnabled())
        		throw new UserNotVerfitedException("Account is not Activited Please Verfity Your Email!");
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
       
            String token = tokenService.generateJwt(auth);
             // userRepository.findByUsername(username).get()
            // raise unverify Exception;
            
            return new LoginResponseDTO(userRepository.findByUsergmail(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }

}