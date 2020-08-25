package com.ag.sjh;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ag.sjh.repositories.one2one.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.ag.sjh.repositories" }) 
@EntityScan(basePackages = { "com.ag.sjh.domains" })
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
