package com.spring.sboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 5)
@Component
public class Many2ManyMappingStartupRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

}
