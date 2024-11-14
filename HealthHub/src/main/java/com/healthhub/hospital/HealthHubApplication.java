package com.healthhub.hospital;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class HealthHubApplication {
	@Value("${openai.key}")
	private String openaiApiKey;
	
	public static void main(String[] args) {
		SpringApplication.run(HealthHubApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate reTemplate = new RestTemplate();
		reTemplate.getInterceptors().add(((request, body,  execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
			return execution.execute(request, body);
		}));
		return reTemplate;
	}
}
