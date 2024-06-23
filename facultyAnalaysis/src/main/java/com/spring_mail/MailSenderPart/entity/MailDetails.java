package com.spring_mail.MailSenderPart.entity;

public class MailDetails {
      private String toMail;
      private String fromMail;
      private String subject;
      private String body;
      public MailDetails()
      {
    	  
      }
	public MailDetails(String toMail, String fromMail, String subject, String body) {
		super();
		this.toMail = toMail;
		this.fromMail = fromMail;
		this.subject = subject;
		this.body = body;
	}
	public String getToMail() {
		return toMail;
	}
	public void setToMail(String toMail) {
		this.toMail = toMail;
	}
	public String getFromMail() {
		return fromMail;
	}
	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "MailDetails [toMail=" + toMail + ", fromMail=" + fromMail + ", subject=" + subject + ", body=" + body
				+ "]";
	}
      
}
