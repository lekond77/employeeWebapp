package com.leon.webapp.exception;

public class TokenNotFoundException extends RuntimeException {
	
	public TokenNotFoundException(String message) {
		super(message);
	}
}