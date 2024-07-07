package com.spring_mail.MailSenderPart.exception;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String message)
    {
   	 super(message);
    }
}