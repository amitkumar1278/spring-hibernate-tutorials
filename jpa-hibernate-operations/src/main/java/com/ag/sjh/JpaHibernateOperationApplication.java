package com.ag.sjh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaHibernateOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateOperationApplication.class, args);
	}

	
	/**
	 * testing post application start component
	 */
//	@Bean
//	public CommandLineRunner mappinngDemo(MovieRepository movieRepository) {
//		return args -> {
//
//			System.out.println("in mappinngDemo");
//		};
//	}
}
