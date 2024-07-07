package com.spring_mail.MailSenderPart.verfity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class VertyController {
     @Autowired
    private VerfityService service;
	 @PostMapping("/verify")
	 public Boolean verifyByCode(@RequestBody VerifyDto verifyDetails )
	 {
		 return service.check(verifyDetails.email(),verifyDetails.code());
	 }
	 @PostMapping("/resend")
	 public String SentTodMail(@RequestBody Email email)
	 {
		  System.out.println(email);
		if(service.resendMail(email.email(),false)) {
			return "Success";
		}
	return "Failed";
	 }
}
 record VerifyDto(String email, long code) {}