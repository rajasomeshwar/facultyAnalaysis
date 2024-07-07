package com.spring.spring_security_learn.exception;

public class InvalidEmailException extends RuntimeException {
     public InvalidEmailException(String message)
     {
    	 super(message);
     }
}
