package com.spring_mail.MailSenderPart.verfity;

import java.time.LocalDateTime;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.spring_security_learn.model.ApplicationUser;
import com.spring.spring_security_learn.repository.UserRepository;
import com.spring_mail.MailSenderPart.entity.MailDetails;
import com.spring_mail.MailSenderPart.exception.AlreadyVertifiedEmailException;
import com.spring_mail.MailSenderPart.exception.InvalidCodeException;
import com.spring_mail.MailSenderPart.exception.NotCreatedByEmailException;
import com.spring_mail.MailSenderPart.service.mailService;

@Service
public class VerfityService {
	
	private RepoVerit repo;
    private UserRepository userrepo;
	
	public VerfityService(RepoVerit repo, UserRepository userrepo, mailService mailservice) {
		super();
		this.repo = repo;
		this.userrepo = userrepo;
		this.mailservice = mailservice;
	}
	
	public Boolean check(String email, long code) {
		// TODO Auto-generated method stub
		System.out.println(code);
	    VerfityItem1 details= repo.findByEmail(email);
	    var dets=checkOfEmail(email);
	     System.out.println("xwal s"+dets+" ");
	     System.out.println("details "+details);
	     LocalDateTime currentDate=LocalDateTime.now();
	     System.out.println(currentDate+" "+details.getDate().isAfter(currentDate));
	     if(details.getDate().isAfter(currentDate) && details.getCode()==code)
	     {
	    	 // unlock the account
	    	  
	    	  dets.setEnabled(true);
	    	  
	    	 repo.delete(details);
	    	 
           return true;
	     }
	     
		throw new InvalidCodeException("Please Enter the Correct code!");
	
	}
	/*
	 * MailDetails {
      private String toMail;
      private String fromMail;
      private String subject;
      private String body;
      	super();
		//this.id = id;
		this.email = gmail;
		this.code = code;
		this.date = date;
	 */
    private mailService mailservice;
	public Boolean resendMail(String email,boolean isFirst) {
		// TODO Auto-generated method stub
       if(isFirst)
       {
    	   VerfityItem1 item=new VerfityItem1(1,email,1112,LocalDateTime.now().plusMinutes(5));
    	   System.out.println(item);
    	   repo.save(item);
       } 
       if(!isFirst)
        checkOfEmail(email);
       Random rnd=new Random();
       long code=100000+rnd.nextLong()%10_0000;
      System.out.println("x "+email);
		String from="rajasomeshwarbitla@gmail.com";
        String subject="Verfication For SpeeD";
        String body="verification code : "+code;
	  var  mailsents =new MailDetails(email,from,subject,body);
	  var item=repo.findByEmail(email);
	  if(item==null) {
		  throw new AlreadyVertifiedEmailException("Email is Already verified! pls Login ");
	  }
	    item.setCode(code);
	    item.setDate(LocalDateTime.now().plusMinutes(15));
	    repo.save(item);
	  mailservice.sentTo(mailsents);
	  // IF data is there ok 
	  
	  // then not ok ;
	   System.out.println("Ok i  reached here");
		return true;
	}
	private ApplicationUser checkOfEmail(String email)
	{
		 var dets=userrepo.findByUsergmail(email).orElse(null);
		    
		    if(dets==null)
		    {
		    	throw new NotCreatedByEmailException("Email never registred in DB!");
		    }
		    if(dets.getEnabled())
		    {
		    	throw new AlreadyVertifiedEmailException("Login without Verification ");
		    }
		    return dets;
	}
    
}
