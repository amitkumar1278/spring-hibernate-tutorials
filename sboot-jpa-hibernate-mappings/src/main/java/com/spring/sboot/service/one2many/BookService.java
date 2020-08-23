package com.spring.sboot.service.one2many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.sboot.domains.one2many.Book;
import com.spring.sboot.repositories.one2many.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * getting all record by using the method findaAll() of CrudRepository
	 */

	@Transactional
	public List<Book> getAllBooks() {
		
		List<Book> books = new ArrayList<>();
		// final Session session = (Session) entityManager.unwrap(Session.class);
		bookRepository.findAll().forEach(book1 -> {
			books.add(book1);
			// book1.getPages();
			book1.getPages().size();
		});
		// session.close();
		return books;
	}

	/**
	 * getting a specific record by using the method findById() of CrudRepository
	 */
	@Transactional
	public Book getBookById(Long bookID) {
		Book book = bookRepository.findById(bookID).get();
		Hibernate.initialize(book.getPages());
		return book;
	}

	/**
	 * saving a specific record by using the method save() of CrudRepository
	 */
	public Book saveOrUpdate(Book book) {
		Book savedBook = bookRepository.save(book);
		return savedBook;
	}

	/**
	 * deleting a specific record by using the method deleteById() of CrudRepository
	 */
	public void delete(Long bookID) {
		bookRepository.deleteById(bookID);
	}


	/**
	 * saving a list of record by using the method saveAll() of CrudRepository
	 */
	public void saveAllBooks(List<Book> books) {
		bookRepository.saveAll(books);
	}

	/**
	 * getting a specific record by using the method getBookByTitle()
	 */
	@Transactional
	public Book getBookByTitle(String title) {
		Book book = bookRepository.findByTitle(title);
		Hibernate.initialize(book.getPages());
		return book;
	}
}
