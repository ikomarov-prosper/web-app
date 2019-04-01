package com.prosper.jsspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.prosper.*")
public class JsSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsSpringBootApplication.class, args);
	}

}
