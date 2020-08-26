package com.ag.sjh;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ag.sjh.pagination.domains.Person;
import com.ag.sjh.pagination.repositories.PersonRepository;

@org.springframework.core.annotation.Order(value = 3)
@Component
public class PaginationStartupRunner implements CommandLineRunner {

	@Autowired
	PersonRepository personRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("\n\n=============================================================================================");
		System.out.println("######################    JPA Pagination demonstration Started    ##########################\n\n");

        List<Person> personList = Arrays.asList(
                new Person("Chris", "Evans", 39),
                new Person("Mark", "Ruffalo", 52),
                new Person("Benedict", "Cumberbatch", 44),
                new Person("Elizabeth", "Olsen", 31),
                new Person("Zoe", "Saldana", 42),
                new Person("Natalie", "Portman", 39),
                new Person("Chris", "Hemsworth", 37),
                new Person("Tom", "Holland", 24),
                new Person("Robert", "Downey, Jr.", 55),
                new Person("Scarlett", "Johansson", 35),
                new Person("Cobie", "Smulders", 38),
                new Person("Tom", "Hiddleston", 39),
                new Person("Jeremy", "Renner", 49)
        );

        personRepository.saveAll(personList);
		

        
		List<Person> savedPersonList = (List<Person>) personRepository.findAll();
		System.out.println("\n######################    All person are below  -> List user  ##########################");
		savedPersonList.forEach(person -> {
			System.out.println(person.toString());
		});
		System.out.println("---------------------------------------------\n");

		
		
        /**
         *  get all persons by last name
         */
        Pageable pageable = PageRequest.of(0, 3);
        Page<Person> personPageByLastName = personRepository.findByLastName("Cumberbatch", pageable);
		System.out.println("\n\n######################   person by lastname, showing in page request    ##########################");
        personPageByLastName.forEach(System.out::println);
        System.out.println("---------------------------------------------\n");
        
        
            
        /**
         * get all persons sorted by their age in the descending order
         */
        Pageable pageable2 = PageRequest.of(0, 5, Sort.by("age").descending());
        Page<Person> personPageOrderByAge = personRepository.findAll(pageable2);
		System.out.println("\n\n######################   person order by age, showing in page request    ##########################");
		personPageOrderByAge.forEach(System.out::println);
        System.out.println("---------------------------------------------\n");
        
        
        /**
         *  skip pagination
         */
        Slice<Person> personSliceBetweenAge = personRepository.findByAgeBetween(35, 50, Pageable.unpaged());
		System.out.println("\n\n######################   person between age, showing in page request    ##########################");
		personSliceBetweenAge.forEach(System.out::println);
        System.out.println("---------------------------------------------\n");
        
        
        
		
		System.out.println("\n\n######################    JPA Pagination demonstration Ended    ##########################");
		System.out.println("===========================================================================================\n\n");

	}

}
