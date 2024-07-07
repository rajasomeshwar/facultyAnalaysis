package com.spring_mail.MailSenderPart.VericationOfAccount;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.spring_security_learn.repository.UserRepository;
import com.spring_mail.MailSenderPart.exception.MailNotFoundException;
import com.spring_mail.MailSenderPart.exception.TokenInvalidException;
import com.spring_mail.MailSenderPart.verfity.Email;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
public class VerificationControler {
    private TokenVerificationService tokenService;
    private VerificationTokenRepo tokenRepository;
	private UserRepository userRepo;

	 public VerificationControler(TokenVerificationService tokenService, VerificationTokenRepo tokenRepository,UserRepository userRepo) {
		super();
		this.tokenService = tokenService;
		this.tokenRepository = tokenRepository;
		this.userRepo=userRepo;
	}
	@GetMapping("/auth/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
		String request=ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		 request=rechangeUrl(request);
		 System.out.println("x+ s " +request);
	      if(token==null)
	      {
	    	  throw new TokenInvalidException("Invalid Token ");
	      }
		 VerficationToken theToken = tokenRepository.findByToken(token);
	     if(theToken==null)
	     {
	    	 throw new TokenInvalidException("Invalid Token ");
	     }
        if (theToken.getUser().isEnabled()){
            return "This account has already been verified, please, login.";
        }
        String verificationResult = tokenService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification token";
    }
				@PostMapping("/auth/verifyEmail/resend")
				 public String sentToEmail(@RequestBody Email email)
				 {
					 String request=ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
					 System.out.println(request);
					 var theUser=userRepo.findByUsergmail(email.email()).orElse(null);
				       if(theUser==null) {
				    	   throw new MailNotFoundException("Not Found Email");
				       }
					 if (theUser.isEnabled()){
				            return "This account has already been verified, please, login.";
				        }
					 tokenService.sentToEmail(email.email(),request);
					 return "Sent";
				 }
				 
				private String rechangeUrl(String request) {
					// TODO Auto-generated method stub
					int ix=0;
					boolean b=false;
				   for(;ix<request.length();ix++) if(request.charAt(ix)==':') {
					   if(b) break;
					   b=true;
				   }
				   StringBuilder stn=new StringBuilder(request.substring(0, ix+1));
				   stn.append("3000");
				   while(ix<request.length() && request.charAt(ix)!='/') ix++;
				   stn.append(request.substring(ix));
				   
					return stn.toString();
					//return "https://faculty-analysis.vercel.app/";
				}
}
