package com.spring.spring_security_learn.errorCustomsized;


import java.time.LocalDateTime;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.spring_security_learn.exception.InvalidEmailException;
import com.spring.spring_security_learn.exception.UserAlreadyExists;
import com.spring.spring_security_learn.exception.UserNotFoundException;
import com.spring.spring_security_learn.exception.UserNotVerfitedException;
import com.spring.spring_security_learn.exception.UserPasswordWrongException;

@ControllerAdvice
public class CustomsizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
 
   @ExceptionHandler(UserNotVerfitedException.class)
   public final ResponseEntity<ErrorDetails> handleUserNotVerfitedExceptions(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(UserNotFoundException.class)
   public final ResponseEntity<ErrorDetails> handleUserNotFoundExceptions(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(UserPasswordWrongException.class)
   public final ResponseEntity<ErrorDetails> handleUserPasswordWrongException(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(InvalidEmailException.class)
   public final ResponseEntity<ErrorDetails> handleInvalidEmailException(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(UserAlreadyExists.class)
   public final ResponseEntity<ErrorDetails> handleUserExistsExceptions(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
//   @ExceptionHandler(Exception.class)
//   public final ResponseEntity<ErrorDetails> handleAllException(Exception ex,WebRequest request)
//   {
//	   ErrorDetails error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
//	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.INTERNAL_SERVER_ERROR);
//   }
   @Override
   public  ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) 
   {  
	   StringBuilder err=new StringBuilder();
	   err.append("Total Error are : "+ex.getErrorCount()+"  errors are ");
	   for (var  e :ex.getAllErrors())
		    err.append(e.getDefaultMessage());
//	   err.append(ex.getFieldError().getDefaultMessage());
	   var error=new ErrorDetails(LocalDateTime.now(),err.toString(),request.getDescription(false));
	   return new ResponseEntity(error,HttpStatus.BAD_REQUEST);
   }
}