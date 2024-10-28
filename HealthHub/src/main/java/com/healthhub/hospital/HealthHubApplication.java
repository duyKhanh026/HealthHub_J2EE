package com.healthhub.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HealthHubApplication {
	public static void main(String[] args) {
		SpringApplication.run(HealthHubApplication.class, args);
	}
}
