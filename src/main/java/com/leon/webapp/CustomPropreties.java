package com.leon.webapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.leon.webapp")
public class CustomPropreties {

	private String props;
	
	public String getApiUrl() {
		return props;
	}
	
	public void setApiUrl(String props) {
		this.props = props;
	}
}
