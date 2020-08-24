package com.spring.sboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.spring.sboot.repositories.one2one.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.spring.sboot.repositories" }) 
@EntityScan(basePackages = { "com.spring.sboot.domains" })
public class JpaHibernateMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateMappingApplication.class, args);
	}

	/**
	 * testing post application start component
	 */
	@Bean
	public CommandLineRunner mappinngDemo(UserRepository userRepository) {
		return args -> {

			System.out.println("in mappinngDemo");
		};
	}
}
