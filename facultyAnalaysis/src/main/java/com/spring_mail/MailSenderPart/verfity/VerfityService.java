package com.spring_mail.MailSenderPart.verfity;

import java.time.LocalDateTime;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.spring_security_learn.repository.UserRepository;
import com.spring_mail.MailSenderPart.entity.MailDetails;
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
	    VerfityItem details= repo.findByEmail(email);
	    if(details==null) return false;
	    
	     LocalDateTime currentDate=LocalDateTime.now();
	     if(details.getDate().isAfter(currentDate) && details.getCode()==code)
	     {
	    	 // unlock the account
	    	  var dets=userrepo.findByUsergmail(email).orElse(null);
	    	  dets.setEnabled(true);
	    	  
	    	 repo.delete(details);
	    	 
           return true;
	     }
	     return false;
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
	public Boolean resendMail(String email) {
		// TODO Auto-generated method stub
       if(null==repo.findByEmail(email))
       {
    	   VerfityItem item=new VerfityItem(1,email,1112,LocalDateTime.now().plusMinutes(5));
    	   repo.save(item);
    	   System.out.println(item);
       }
       Random rnd=new Random();
       long code=100000+rnd.nextLong()%10_0000;
      System.out.println("x "+email);
		String from="rajasomeshwarbitla@gmail.com";
        String subject="Verfication For SpeeD";
        String body="verification code : "+code;
	  var  mailsents =new MailDetails(email,from,subject,body);
	  var item=repo.findByEmail(email);
	    item.setCode(code);
	    repo.save(item);
	  mailservice.sentTo(mailsents);
	  // IF data is there ok 
	  
	  // then not ok ;
	   System.out.println("Ok i  reached here");
		return true;
	}
    
}
