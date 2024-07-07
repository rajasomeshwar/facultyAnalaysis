package com.spring_mail.MailSenderPart.errorCustomsized;


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

import com.spring.spring_security_learn.errorCustomsized.ErrorDetails;
import com.spring.spring_security_learn.exception.InvalidEmailException;
import com.spring.spring_security_learn.exception.UserAlreadyExists;
import com.spring.spring_security_learn.exception.UserNotFoundException;
import com.spring.spring_security_learn.exception.UserNotVerfitedException;
import com.spring.spring_security_learn.exception.UserPasswordWrongException;
import com.spring_mail.MailSenderPart.exception.AlreadyVertifiedEmailException;
import com.spring_mail.MailSenderPart.exception.InvalidCodeException;
import com.spring_mail.MailSenderPart.exception.MailNotFoundException;
import com.spring_mail.MailSenderPart.exception.NotCreatedByEmailException;
import com.spring_mail.MailSenderPart.exception.TokenInvalidException;

@ControllerAdvice
public class CustomsizedResponseEntityExceptionHandlera extends ResponseEntityExceptionHandler {
 
   @ExceptionHandler(AlreadyVertifiedEmailException.class)
   public final ResponseEntity<ErrorDetails> HandleAlreadyVertifiedEmailException(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(TokenInvalidException.class)
   public final ResponseEntity<ErrorDetails> HandleTokenInvalidException(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(MailNotFoundException.class)
   public final ResponseEntity<ErrorDetails> HandleMailNotFoundException(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(InvalidCodeException.class)
   public final ResponseEntity<ErrorDetails> handleInvalidCodeException(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(NotCreatedByEmailException.class)
   public final ResponseEntity<ErrorDetails> handleNotCreatedByEmailException(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   
}