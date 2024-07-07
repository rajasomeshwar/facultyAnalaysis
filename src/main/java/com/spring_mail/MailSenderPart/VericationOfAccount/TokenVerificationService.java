package com.spring_mail.MailSenderPart.VericationOfAccount;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class TokenVerificationService {
	public TokenVerificationService(VerificationTokenRepo tokenRepository, UserRepository userRepository,
			JavaMailSender mailservice) {
		super();
		this.tokenRepository = tokenRepository;
		this.userRepository = userRepository;
		this.mailSender = mailservice;
	}
	private VerificationTokenRepo tokenRepository;
	private UserRepository userRepository;
	private final JavaMailSender mailSender;
    public void saveUserVerificationToken(ApplicationUser theUser, String token) {
    	
        var verificationToken = tokenRepository.findByUser(theUser);
        if(verificationToken==null)
        		verificationToken=new VerficationToken(token, theUser);
        verificationToken.setToken(token);
        verificationToken.setExpireDate(LocalDateTime.now().plusMinutes(5));
        tokenRepository.save(verificationToken);
    }
    public String validateToken(String theToken) {
    	VerficationToken token = tokenRepository.findByToken(theToken);
        if(token == null){
            return "Invalid verification token";
        }
        System.out.println(token+" her token");
        ApplicationUser user = token.getUser();
         LocalDateTime time=LocalDateTime.now();
        if (!token.getExpireDate().isAfter(time)){
            tokenRepository.delete(token);
            return "Token already expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
       // tokenRepository.delete(token);
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
	        //2. Create a verification token for the user
	        String verificationToken = UUID.randomUUID().toString();
	        //3. Save the verification token for the user
	        saveUserVerificationToken(theUser, verificationToken);
	        //4 Build the verification url to be sent to the user
	        String url = EditUrl(request)+"?token="+verificationToken;
	        //5. Send the email.
	        try {
	            sendVerificationEmail(url,theUser);
	        } catch (MessagingException | UnsupportedEncodingException e) {
	            throw new RuntimeException(e);
	      
	        }
	        System.out.println("Click the link to verify your registration :  "+ url);
	    }
	    
	    
	    
	    private String EditUrl(String request) {
			// TODO Auto-generated method stub
	    	// TODO Auto-generated method stub
			int ix=0;
			// to do static ;
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
			//return "https://faculty-analysis-omlv66vuf-rajasomeshwar-s-projects.vercel.app/auth/verifyEmail";
		}
	    public void sendVerificationEmail(String url, ApplicationUser theUser) throws MessagingException, UnsupportedEncodingException {
	        String subject = "Email Verification";
	        String senderName = "User Registration Portal Service";
	        String mailContent = "<html><body>" +
	                "<p>Hi, " + theUser.getUsergmail() + ",</p>" +
	                "<p>Thank you for registering with us. Please, follow the link below to complete your registration:</p>" +
	                "<p><a href=\"" + url + "\">Verify your email to activate your account</a></p>" +
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
