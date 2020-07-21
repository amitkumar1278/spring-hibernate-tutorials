package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		System.out.println("Spring Boot Application starting!!");
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}
