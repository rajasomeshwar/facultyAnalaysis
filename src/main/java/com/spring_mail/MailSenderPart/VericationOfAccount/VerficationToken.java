package com.spring_mail.MailSenderPart.VericationOfAccount;

import java.time.LocalDateTime;

import com.spring.spring_security_learn.model.ApplicationUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class VerficationToken {
	      @Id
	     @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
   private String token;
   private LocalDateTime expireDate;
   @OneToOne
   @JoinColumn(name = "user_id")
   private ApplicationUser user;
   private static final int EXPIRATION_TIME = 15;
   public VerficationToken(String token, ApplicationUser user) {
       super();
       this.token = token;
       this.user = user;
       this.expireDate = this.getTokenExpirationTime();
   }

   public VerficationToken(String token) {
       super();
       this.token = token;
       this.expireDate = this.getTokenExpirationTime();
   }
   public VerficationToken() {
       super();
       
   }
  
   public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public LocalDateTime getExpireDate() {
	return expireDate;
}

public void setExpireDate(LocalDateTime expireDate) {
	this.expireDate = expireDate;
}

public ApplicationUser getUser() {
	return user;
}

public void setUser(ApplicationUser user) {
	this.user = user;
}

public static int getExpirationTime() {
	return EXPIRATION_TIME;
}

@Override
public String toString() {
	return "VerficationToken [Token=" + token + ", expireDate=" + expireDate + ", user=" + user + "]";
}

public LocalDateTime getTokenExpirationTime() {
	 return  LocalDateTime.now().plusMinutes(EXPIRATION_TIME);
   }
   
}
