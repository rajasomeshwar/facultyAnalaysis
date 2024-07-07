package com.profileAnalysisTables.errorCustomsized;


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

import com.profileAnalysisTables.exception.SessionExpireException;
import com.profileAnalysisTables.exception.UnauthorizedActionException;
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
public class CustomsizedResponseEntityExceptionHandlera1 extends ResponseEntityExceptionHandler {
 
  
   @ExceptionHandler(SessionExpireException.class)
   public final ResponseEntity<ErrorDetails> handleNotCreatedByEmailException(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(UnauthorizedActionException.class)
   public final ResponseEntity<ErrorDetails> handleUnauthorizedActionException(Exception ex,WebRequest request)
   {
	   var error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
   }
   
}