package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * 
 * @author amit kumar
 * Using Spring initializr https://start.spring.io.
 * 
 */
@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
		System.out.println("\n ===================  Spring Boot Application started!! ===================");
	}

}
