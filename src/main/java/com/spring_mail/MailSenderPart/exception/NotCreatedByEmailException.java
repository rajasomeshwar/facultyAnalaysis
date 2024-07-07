package com.spring_mail.MailSenderPart.exception;

public class NotCreatedByEmailException extends RuntimeException {
     public NotCreatedByEmailException(String message)
     {
    	 super(message);
     }
}