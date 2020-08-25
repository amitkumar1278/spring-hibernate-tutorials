package com.ag.sjh.repositories.one2many;

import org.springframework.data.repository.CrudRepository;

import com.ag.sjh.domains.one2many.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	public Book findByTitle(String title);

}
