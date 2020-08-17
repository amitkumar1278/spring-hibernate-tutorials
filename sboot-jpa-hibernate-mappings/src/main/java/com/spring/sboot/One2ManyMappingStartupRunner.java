package com.spring.sboot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.spring.sboot.domains.one2many.Book;
import com.spring.sboot.domains.one2many.Page;
import com.spring.sboot.repositories.one2many.BookRepository;
import com.spring.sboot.repositories.one2many.PageRepository;
import com.spring.sboot.service.one2many.BookService;

@Order(value = 4)
@Component
public class One2ManyMappingStartupRunner implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookService bookService;

	@Autowired
	PageRepository pageRepository;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("\n\n=============================================================================================");
		System.out.println("######################    One2Many Mapping demonstration Started    #########################\n\n");

		/**
		 * Creating a book with multiple pages
		 */
		Book immortalOfMeluha = new Book("The Shiva Trilogy Book: Immortals of Meluha", "Amish Tripathi", "ISBN-10: 9380658745");
		List<Page> meluhaPages = Arrays.asList(
				new Page(1, "content 1", "first", immortalOfMeluha),
				new Page(2, "content 2", "second", immortalOfMeluha),
				new Page(3, "content 3", "third", immortalOfMeluha));
		Set<Page> meluhaPageSet = meluhaPages.stream().collect(Collectors.toSet()); 
		immortalOfMeluha.setPages(meluhaPageSet);
		bookRepository.save(immortalOfMeluha);

		Book secretOfTheNagas  = new Book("The Shiva Trilogy Book: Secret of the Nagas ", "Amish Tripathi", "ISBN-13: 978-9380658797");
		Set<Page> naagsPageSet = Stream.of(new Page(1, "content 1", "first", secretOfTheNagas), 
				new Page(4, "content 4", "fourth", secretOfTheNagas),
				new Page(5, "content 5", "fifth", secretOfTheNagas))
				.collect(Collectors.toSet());
		secretOfTheNagas.setPages(naagsPageSet);
		bookRepository.save(secretOfTheNagas);

		Book theOathOfTheVayuputras = new Book("The Shiva Trilogy Book: The Oath of The Vayuputras", "Amish Tripathi", "ISBN-16: 9781681445045");
		Page vayuputraPage1 =  new Page(1, "content 1", "first", theOathOfTheVayuputras);
		Page vayuputraPage2 =  new Page(6, "content 6", "sixth", theOathOfTheVayuputras);
		Page vayuputraPage3 =  new Page(7, "content 7", "seevnth", theOathOfTheVayuputras);

		Set<Page> vayuputraPageSet = new HashSet<Page>(Arrays.asList(vayuputraPage1, vayuputraPage2, vayuputraPage3));
		theOathOfTheVayuputras.setPages(vayuputraPageSet);
		bookRepository.save(theOathOfTheVayuputras);



		List<Book> allbooks = (List<Book>) bookService.getAllBooks();

		System.out.println("\n######################    All Books are below  -> List user  ##########################");

		allbooks.forEach(book -> {
			System.out.println("name: "+book.getTitle()+", ISBN: "+book.getIsbn());
			System.out.println(book.toString());
			System.out.println("Printing page detail::");
			book.getPages().forEach(page -> {
				System.out.println(page.toString());
			});
			System.out.println("---------------------------------------------\n");
		});








		System.out.println("\n\n######################    One2Many Mapping demonstration Ended    #########################");
		System.out.println("===========================================================================================\n\n");


	}

}
