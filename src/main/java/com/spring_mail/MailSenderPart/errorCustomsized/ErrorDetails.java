package com.spring_mail.MailSenderPart.errorCustomsized;


import java.time.LocalDateTime;
// this class used for customsize response back to user 
public class ErrorDetails {
 private LocalDateTime timestamp;
 private String message;
 private String details;
public LocalDateTime getTimestamp() {
	return timestamp;
}
public String getMessage() {
	return message;
}
public String getDetails() {
	return details;
}
public ErrorDetails(LocalDateTime timestamp, String message, String details) {
	super();
	this.timestamp = timestamp;
	this.message = message;
	this.details = details;
}

}
