package com.nagarro.assignment5.exception;

public class CustomException extends Exception{
    
    private static final long serialVersionUID = 5613473066748709093L;

    public CustomException(String message) {
	super(message);
    }

    public CustomException(String message, Throwable cause) {
	super(message, cause);
    }
}
