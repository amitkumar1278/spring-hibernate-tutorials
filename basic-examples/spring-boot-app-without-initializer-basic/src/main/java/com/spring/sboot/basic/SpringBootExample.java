package com.spring.sboot.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * 
 * @author amit kumar
 * manual configuration, without spring initializer
 */

@SpringBootApplication 
public class SpringBootExample {

	public static void main(String[] args)  
	{    
		SpringApplication.run(SpringBootExample.class, args);    
	}   
}
