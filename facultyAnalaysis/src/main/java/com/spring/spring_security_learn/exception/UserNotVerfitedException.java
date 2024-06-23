package com.spring.spring_security_learn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class UserNotVerfitedException extends RuntimeException {
	public UserNotVerfitedException(String message)
	{
		super(message);
	}

}
