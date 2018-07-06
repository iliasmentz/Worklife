package com.linkedin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.linkedin")
@SpringBootApplication
public class LinkedInApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkedInApplication.class, args);
	}
}
