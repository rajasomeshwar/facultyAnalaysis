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

import com.spring.spring_security_learn.exception.UserNotFoundException;
import com.spring.spring_security_learn.exception.UserNotVerfitedException;

@ControllerAdvice
public class CustomsizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
   @ExceptionHandler(Exception.class)
   public final ResponseEntity<ErrorDetails> handleAllException(Exception ex,WebRequest request)
   {
	   ErrorDetails error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.INTERNAL_SERVER_ERROR);
   }
   @ExceptionHandler(UserNotVerfitedException.class)
   public final ResponseEntity<ErrorDetails> handleUerNotVerfitedExceptions(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(UserNotFoundException.class)
   public final ResponseEntity<ErrorDetails> handleUerNotFoundExceptions(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
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