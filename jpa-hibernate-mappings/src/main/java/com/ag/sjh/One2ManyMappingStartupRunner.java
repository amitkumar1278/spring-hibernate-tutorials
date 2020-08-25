package com.ag.sjh;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.ag.sjh.domains.one2many.Book;
import com.ag.sjh.domains.one2many.Page;
import com.ag.sjh.repositories.one2many.BookRepository;
import com.ag.sjh.repositories.one2many.PageRepository;
import com.ag.sjh.service.one2many.BookService;

@org.springframework.core.annotation.Order(value = 4)
@Component
public class One2ManyMappingStartupRunner implements CommandLineRunner {

	@Autowired
	BookService bookService;
	
	@Autowired
	BookRepository bookRepository;
	
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
		immortalOfMeluha = bookService.saveOrUpdate(immortalOfMeluha);
		
		
		
		Book immortalOfMeluha2 = bookService.getBookById(immortalOfMeluha.getId());
		System.out.println("\n######################    Saved Meluha   ##########################");
		System.out.println(immortalOfMeluha2.toString());
		System.out.println(immortalOfMeluha2.getPages().toString()+"\n\n");
		
		
		
		Book secretOfTheNagas  = new Book("The Shiva Trilogy Book: Secret of the Nagas ", "Amish Tripathi", "ISBN-13: 978-9380658797");
		Set<Page> naagsPageSet = Stream.of(new Page(1, "content 1", "first", secretOfTheNagas), 
				new Page(4, "content 4", "fourth", secretOfTheNagas),
				new Page(5, "content 5", "fifth", secretOfTheNagas))
				.collect(Collectors.toSet());
		secretOfTheNagas.setPages(naagsPageSet);

		Book theOathOfTheVayuputras = new Book("The Shiva Trilogy Book: The Oath of The Vayuputras", "Amish Tripathi", "ISBN-16: 9781681445045");
		Page vayuputraPage1 =  new Page(1, "content 1", "first", theOathOfTheVayuputras);
		Page vayuputraPage2 =  new Page(6, "content 6", "sixth", theOathOfTheVayuputras);
		Page vayuputraPage3 =  new Page(7, "content 7", "seevnth", theOathOfTheVayuputras);
		Set<Page> vayuputraPageSet = new HashSet<Page>(Arrays.asList(vayuputraPage1, vayuputraPage2, vayuputraPage3));
		theOathOfTheVayuputras.setPages(vayuputraPageSet);
		
		
		
		bookService.saveAllBooks(Arrays.asList(secretOfTheNagas, theOathOfTheVayuputras));
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
		System.out.println("---------------------------------------------\n");


		
		Book titledBook = bookService.getBookByTitle("The Shiva Trilogy Book: Secret of the Nagas ");
		System.out.println("\n######################    get book by title: "+titledBook.getTitle()+"   ##########################");
		System.out.println(titledBook.toString()+"\n\n");


	
		Order[] order = { new Order(Direction.ASC, "chapter"), new Order(Direction.ASC, "content") };		
		List<Page> pagesOrder = pageRepository.findByBook(titledBook, Sort.by(order));
		List<Page> pages = pageRepository.findByBook(titledBook, Sort.by(Direction.DESC, "content"));
		
		
		System.out.println("\n######################    get pages by Book: "+titledBook.getTitle()+"  ##########################");
		pages.forEach(page -> System.out.println(page.toString()));
		System.out.println("------------------------------------------------------------\n");
		pagesOrder.forEach(page -> System.out.println(page.toString()));
		
		
		
		
		
		System.out.println("\n\n######################    One2Many Mapping demonstration Ended    #########################");
		System.out.println("===========================================================================================\n\n");


	}
	
}
