package com.spring_mail.MailSenderPart.exception;

public class InvalidCodeException extends RuntimeException {
     public InvalidCodeException(String message)
     {
    	 super(message);
     }
}