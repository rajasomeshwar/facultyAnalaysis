package com.profileAnalysisTables.exception;

public class SessionExpireException extends RuntimeException {
    public SessionExpireException(String message)
    {
   	 super(message);
    }
}