package com.spring_mail.MailSenderPart.exception;

public class MailNotFoundException extends RuntimeException {
    public MailNotFoundException(String message)
    {
   	 super(message);
    }
}