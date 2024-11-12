package com.leon.webapp.entity;

import org.springframework.http.HttpStatusCode;

public class AuthResponse {

	private String message;
	private HttpStatusCode statusCode;
	
	public AuthResponse(HttpStatusCode statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public HttpStatusCode getStatusCode() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}

	
	
}
