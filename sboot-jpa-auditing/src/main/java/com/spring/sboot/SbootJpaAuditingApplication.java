package com.spring.sboot;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.sboot.model.Todo;
import com.spring.sboot.repositories.TodoRepository;



@SpringBootApplication
public class SbootJpaAuditingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootJpaAuditingApplication.class, args);
	}

	@Bean
	public CommandLineRunner auditingDemo(TodoRepository todoRepository) {
		return args -> {

			/**
			 *  create new todos
			 */
			todoRepository.saveAll(Arrays.asList(
					new Todo("Buy Grocery", false), 
					new Todo("Prepare dinner", false),
					new Todo("Call Papa", false), 
					new Todo("Clean house", false), 
					new Todo("Evening Walk", false),
					new Todo("Play ludo with wife", false), 
					new Todo("Watch Netflix", false)));
			
			/**
			 * retrieve all todos
			 */
			Iterable<Todo> todos = todoRepository.findAll();
			
			/**
			 * print all todos
			 */
			System.out.println("\n\n\n\nAll todos are below ");
			System.out.println("################################################");
			todos.forEach(System.out::println);
			
			
		};
		
	}
}
