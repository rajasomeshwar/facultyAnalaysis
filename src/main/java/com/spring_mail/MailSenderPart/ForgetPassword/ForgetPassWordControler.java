package com.spring_mail.MailSenderPart.ForgetPassword;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.spring_security_learn.exception.UserNotVerfitedException;
import com.spring_mail.MailSenderPart.exception.TokenInvalidException;
import com.spring_mail.MailSenderPart.verfity.Email;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
public class ForgetPassWordControler {
    private ForgetPasswordService tokenService;
    private VerificationTokenForgetPasswordRepo tokenRepository;
	

	 public ForgetPassWordControler(ForgetPasswordService tokenService, VerificationTokenForgetPasswordRepo tokenRepository) {
		super();
		this.tokenService = tokenService;
		this.tokenRepository = tokenRepository;
	}
	@PostMapping("/auth/forgetpassword")
    public String ChangePasswordByToken(@RequestBody ForgetPasswordReq forgetPasswordData){
		String token=forgetPasswordData.token();
		VerficationTokenForgetPassword theuser = tokenRepository.findByToken(token);
		if(token==null || theuser==null)
		{
			throw new TokenInvalidException("Invalid token");
		}
		String request=ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		 System.out.println("x+ s " +request);
        if (!theuser.getUser().isEnabled()){
            throw new UserNotVerfitedException("User email is not verfied. Pls Verify Your email");
        }
        String verificationResult = tokenService.validateToken(token,forgetPasswordData.password());
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Successfully rest your ";
        }
        return verificationResult;
    }
	 @PostMapping("/auth/forgetpassword/resend")
	 public String sentToEmail(@RequestBody Email email)
	 {
		 System.out.println("here sneds");
		 String request=ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		 System.out.println(request);
		 tokenService.sentToEmail(email.email(),request);
		 return "Sent";
	 }
	 
}
