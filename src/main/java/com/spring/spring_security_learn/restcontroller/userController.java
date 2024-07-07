package com.spring.spring_security_learn.restcontroller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_security_learn.repository.UserRepo;
import com.spring.spring_security_learn.service.Password;
import com.spring.spring_security_learn.userD.userOwn;

//@RestController
public class userController {
   private	 UserRepo userdetailsRepo;
   private Password passwordencoder;
     public userController(UserRepo userdetailsRepo,Password passwordencoder) {
	super();
	 this.passwordencoder=passwordencoder;
	this.userdetailsRepo = userdetailsRepo;
}

	@PostMapping("/register")
     public String CreateUserOwn(@RequestBody userOwn user)
     {
		String stn=user.getPassword();
		user.setPassword(passwordencoder.passwordEncoder(stn));
    	 var userOwn=userdetailsRepo.save( user);
    	 // verification is mail sent to mail ;
    	 
    	 return "Verification code is sent your email ";
    	 
     }
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder()
//	{
//    return		new BCryptPasswordEncoder();
//	}
//	  
}
