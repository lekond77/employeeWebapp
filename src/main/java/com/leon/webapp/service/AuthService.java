package com.leon.webapp.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leon.webapp.CustomPropreties;
import com.leon.webapp.entity.AuthResponse;

@Service
public class AuthService {

	
	@Autowired
	private CustomPropreties props;


	public AuthResponse login(String username, String password) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String auth = username + ":" + password;
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

		HttpHeaders headers = new HttpHeaders();

		headers.set("Authorization", "Basic " + encodedAuth);

		HttpEntity<String> entity = new HttpEntity<>(headers);
	
		try {
			
			ResponseEntity<String>  response = restTemplate.exchange(props.getApiUrl() + "/login", HttpMethod.POST, entity, String.class);
			
			return new AuthResponse(response.getStatusCode(), response.getBody());

		}catch(Exception e) {
			
			return new AuthResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials");
		}
		
	}
	
}
