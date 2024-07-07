package com.spring_mail.MailSenderPart.exception;

public class AlreadyVertifiedEmailException extends RuntimeException {
     public AlreadyVertifiedEmailException(String message)
     {
    	 super(message);
     }
}