package com.denik.vy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class LessonBoostrapSecurity312Application {

	public static void main(String[] args) {
		SpringApplication.run(LessonBoostrapSecurity312Application.class, args);
	}

}
