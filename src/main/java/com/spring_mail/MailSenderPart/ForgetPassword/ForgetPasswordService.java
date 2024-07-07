package com.spring_mail.MailSenderPart.ForgetPassword;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.spring_security_learn.exception.UserNotVerfitedException;
import com.spring.spring_security_learn.model.ApplicationUser;
import com.spring.spring_security_learn.repository.UserRepository;
import com.spring_mail.MailSenderPart.entity.MailDetails;
import com.spring_mail.MailSenderPart.exception.MailNotFoundException;
import com.spring_mail.MailSenderPart.service.mailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
@Transactional
public class ForgetPasswordService {
	private VerificationTokenForgetPasswordRepo tokenRepository;
	private UserRepository userRepository;
	 private final PasswordEncoder passwordEncoder;
	private final JavaMailSender mailSender;
	public ForgetPasswordService(VerificationTokenForgetPasswordRepo tokenRepository, UserRepository userRepository,
			JavaMailSender mailservice,PasswordEncoder passwordEncoder) {
		super();
		this.tokenRepository = tokenRepository;
		this.userRepository = userRepository;
		this.mailSender = mailservice;
		this. passwordEncoder= passwordEncoder;
	}
    public void saveUserVerificationToken(ApplicationUser theUser, String token) {
    	
        var verificationToken = tokenRepository.findByUser(theUser);
        if(verificationToken==null)
        		verificationToken=new VerficationTokenForgetPassword(token, theUser);
        verificationToken.setToken(token);
        verificationToken.setExpireDate(LocalDateTime.now().plusMinutes(15));
        tokenRepository.save(verificationToken);
    }
    public String validateToken(String theToken, String password) {
        VerficationTokenForgetPassword token = tokenRepository.findByToken(theToken);
     //   System.out.println(token+" Inside validate");
        if (token == null) {
            return "Invalid verification token";
        }
        
        LocalDateTime time = LocalDateTime.now();
       System.out.println(" her " +time+" "+token.getExpireDate());
        if (!token.getExpireDate().isAfter(time)) {
            return "Token already expired";
        }
        ApplicationUser user = token.getUser();
        
        // Encode the password before saving
        String encodedPassword =passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        
        // Optionally delete the token after successful password reset
        tokenRepository.delete(token);
        
        return "valid";
    }
	
	
	    public void sentToEmail(String email,String request) {
	        // 1. Get the newly registered user
	    	
	         var theUser=userRepository.findByUsergmail(email).orElse(null);
	        System.out.println(theUser+" here ");
	         if(theUser==null)
	         {
	        	 throw new MailNotFoundException("Not Found Email");
	         }
	         if(!theUser.getEnabled())
	         {
	        	 throw new UserNotVerfitedException("Pls Verify the Email ");
	         }
	         
	        //2. Create a verification token for the user
	        String verificationToken = UUID.randomUUID().toString();
	        //3. Save the verification token for the user
	        saveUserVerificationToken(theUser, verificationToken);
	        //4 Build the verification url to be sent to the user
	        String url = EditUrl(request)+"?token="+verificationToken;
	        //5. Send the email.
	        try {
	        	sendForgotPasswordEmail(url,theUser);
	        } catch (MessagingException | UnsupportedEncodingException e) {
	            throw new RuntimeException(e);
	        }
	        System.out.println("Click the link to verify your registration :  "+ url);
	    }
	    
	    
	    
	    private String EditUrl(String request) {
			// TODO Auto-generated method stub
	    	System.out.println("I'm here at Edit url "+request);
	       int ix=0;
	    	boolean b=false;
	    	while(ix<request.length()){
	    		if(request.charAt(ix)==':')
	    		{
	    			if(b) break;
	    			b=true;
	    		}
	    		ix++;
	    	}
	        int y=ix;
	        while(ix<request.length() && request.charAt(ix)!='/') ix++;
			return request.substring(0,y+1)+"3000"+request.substring(ix,request.length()-7);
			// return "https://faculty-analysis.vercel.app/auth/forgetpassword";
		}
	    public void sendForgotPasswordEmail(String url, ApplicationUser theUser) throws MessagingException, UnsupportedEncodingException {
	    	   String subject = "Password Reset Request";
	    	    String senderName = "User Registration Portal Service";
	    	    String mailContent = "<html><body>" +
	    	            "<p>Hi, " + theUser.getUsergmail() + ",</p>" +
	    	            "<p>We received a request to reset your password. Please, follow the link below to reset your password:</p>" +
	    	            "<p><a href=\"" + url + "\">Reset your password</a></p>" +
	    	            "<p>If you did not request a password reset, please ignore this email or contact support.</p>" +
	    	            "<p>Thank you,<br>User Registration Portal Service</p>" +
	    	            "</body></html>";
	    	    
	    	    MimeMessage message = mailSender.createMimeMessage();
	    	    MimeMessageHelper messageHelper = new MimeMessageHelper(message);
	    	    messageHelper.setFrom("rajasomeshwarbitla@gmail.com", senderName);
	    	    messageHelper.setTo(theUser.getUsergmail());
	    	    messageHelper.setSubject(subject);
	    	    messageHelper.setText(mailContent, true);
	    	    mailSender.send(message);
	    }

	    public String applicationUrl(HttpServletRequest request) {
	        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	    }

}
