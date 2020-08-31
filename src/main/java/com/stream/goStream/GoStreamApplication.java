package com.stream.goStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GoStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoStreamApplication.class, args);
	}

}
