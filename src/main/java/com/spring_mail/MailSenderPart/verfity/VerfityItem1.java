package com.spring_mail.MailSenderPart.verfity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 @Entity
 @Data
 @Table
public class VerfityItem1 {
	 @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	  @Column(unique=true)
     private String email;
     private long code;
     private LocalDateTime date;
          public VerfityItem1()
          {
        	  super();
          }
		public VerfityItem1(int id, String gmail, long code, LocalDateTime date) {
		super();
		this.id = id;
		this.email = gmail;
		this.code = code;
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String gmail) {
		this.email = gmail;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "VerfityItem [gmail=" + email + ", code=" + code + ", date=" + date + "]";
	} 
     
}
