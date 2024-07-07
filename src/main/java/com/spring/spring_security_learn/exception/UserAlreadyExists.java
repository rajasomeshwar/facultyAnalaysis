package com.spring.spring_security_learn.exception;

public class UserAlreadyExists extends RuntimeException {
	 public UserAlreadyExists (String message)
	  {
		  super(message);
	  }
}
