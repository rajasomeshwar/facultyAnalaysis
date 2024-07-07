package com.spring.spring_security_learn.service;


import java.util.HashSet;


import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.profileAnalysisTables.FacultyInfo.FacultyInformationRepo;
import com.profileAnalysisTables.FacultyInfo.FacultyInformationT;
import com.spring.spring_security_learn.exception.InvalidEmailException;
import com.spring.spring_security_learn.exception.UserAlreadyExists;
import com.spring.spring_security_learn.exception.UserNotFoundException;
import com.spring.spring_security_learn.exception.UserNotVerfitedException;
import com.spring.spring_security_learn.exception.UserPasswordWrongException;
import com.spring.spring_security_learn.model.*;
import com.spring.spring_security_learn.repository.RoleRepository;
import com.spring.spring_security_learn.repository.UserRepository;
import com.spring_mail.MailSenderPart.VericationOfAccount.TokenVerificationService;
import com.spring_mail.MailSenderPart.verfity.VerfityService;

import jakarta.servlet.http.HttpServletRequest;


@Service
@Transactional
public class AuthenticationService {
   
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
  
	 private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	private VerfityService sendermail;
    private final UserRepository userRepository;

  
    private final RoleRepository roleRepository;
    private final FacultyInformationRepo InfoFacultyRepository;
  
    private PasswordEncoder passwordEncoder;

 
    private AuthenticationManager authenticationManager;

    private TokenVerificationService tokenVerificationService;
    private TokenService tokenService;
@Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService,VerfityService sendermail,
			TokenVerificationService tokenVerificationService,FacultyInformationRepo InfoFacultyRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
		this.sendermail=sendermail;
		this.tokenVerificationService=tokenVerificationService;
	     this.InfoFacultyRepository=InfoFacultyRepository;
	}

	public String registerUser(String username, String password,String mobileNo,FacultyInformationT data){
         if(!isEmailValid(username))
         {
        	 throw new InvalidEmailException("Please enter valid email");
         }
         if(data==null) {
        	 throw new InvalidEmailException("Invalid data fields");
         }
    	// check if exists;
    	System.out.println("her ");
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
      
        authorities.add(userRole);
        if(userRepository.existsByUsergmail(username))
        {
        	throw new UserAlreadyExists("Email is already exists ");
        }
        
        ApplicationUser userdetails= userRepository.save(new ApplicationUser(0, username, encodedPassword,mobileNo, false, authorities,data)); 
      // boolean isSent= sendCodeTomail(username);
        // add more info
       
        
       boolean bylink=sendLinkTomail(username);
        if(bylink) {
           
            return "Verify the Email Pls";
            }
        return "Try Again";
        
	}
    private boolean sendLinkTomail(String username) {
		// TODO Auto-generated method stub
    	 String requestUrl = rechangeUrl("");
    	 System.out.println("IM here link"+requestUrl);
    	 tokenVerificationService.sentToEmail(username, requestUrl);
    	 return true;
	}
    private String rechangeUrl(String request) {
	
	   
		return "http://localhost:3000/auth/verifyEmail";
		//return "https://faculty-analysis.vercel.app/auth/verifyEmail";
	}

	private boolean sendCodeTomail(String email)
    {
    	  boolean isSent=sendermail.resendMail(email, true);
          return isSent;
          
    }
    private boolean isEmailValid(String email) {
		// TODO Auto-generated method stub
    	  if (email == null) {
              return false;
          }
          Matcher matcher = EMAIL_PATTERN.matcher(email);
          return matcher.matches();
	}

	public LoginResponseDTO loginUser(String username, String password){

        try{
        	  if(!isEmailValid(username))
              {
             	 throw new InvalidEmailException("Please enter valid email");
              }
        	System.out.println(" her login serivce"); 
        	System.out.println(username);
        	//System.out.println("z "+userRepository.findAll());
        	var user=userRepository.findByUsergmail(username).orElse(null);
        	System.out.println(" her login serivce"+user);
        	if(user==null)
        		 throw new UserNotFoundException("Email does not  exists! ");
        	System.out.println("x " +user);
        	if(!user.getEnabled())
        		throw new UserNotVerfitedException("Account is not Activited Please Verfity Your Email!");
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
       
            String token = tokenService.generateJwt(auth);
             // userRepository.findByUsername(username).get()
            // raise unverify Exception;
             if(token==null)
             {
            	 //password mismatch
            	 throw new UserPasswordWrongException("Password is Wrong!");
            	 
             }
            return new LoginResponseDTO(userRepository.findByUsergmail(username).get(), token);

        } catch(AuthenticationException e){
        	throw new UserPasswordWrongException("Password is Wrong!");
        }
    }

}