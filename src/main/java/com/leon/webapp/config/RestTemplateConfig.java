package com.leon.webapp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.leon.webapp.exception.TokenNotFoundException;

import jakarta.servlet.http.HttpSession;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate(HttpSession session) {

		RestTemplate restTemplate = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

		interceptors.add((request, body, execution) -> {

			String token = (String) session.getAttribute("token");
			if (token != null) {
				request.getHeaders().set("Authorization", "Basic " + token);
			}else {
				throw new TokenNotFoundException("Token not found. Please log in.");
			}
	
			return execution.execute(request, body);
		});

		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}
}
