package com.ag.sjh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ag.sjh.queries.custom.repositories.NoteRepository;

@org.springframework.core.annotation.Order(value = 4)
@Component
public class QueryCustomStartupRunner implements CommandLineRunner {

	@Autowired
	NoteRepository noteRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("\n\n=============================================================================================");
		System.out.println("######################    Custom Query demonstration Started    ##########################\n\n");

		
		
		
		System.out.println("\n\n######################    Custom Query demonstration Ended    ##########################");
		System.out.println("===========================================================================================\n\n");

	}

}
