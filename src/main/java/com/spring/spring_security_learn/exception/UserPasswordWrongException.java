package com.spring.spring_security_learn.exception;

public class UserPasswordWrongException extends RuntimeException  {
	public UserPasswordWrongException(String message)
	  {
		  super(message);
	  }
}
