package com.healthhub.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HealthHubApplication {
	public static void main(String[] args) {
		SpringApplication.run(HealthHubApplication.class, args);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		// Mã hóa mật khẩu
		String rawPassword = "123";
		String encodedPassword = passwordEncoder.encode(rawPassword);
		System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);

		// Kiểm tra mật khẩu
		boolean isPasswordMatch = passwordEncoder.matches("123", encodedPassword);
		System.out.println("Mật khẩu khớp: " + isPasswordMatch);
		
	}

}
