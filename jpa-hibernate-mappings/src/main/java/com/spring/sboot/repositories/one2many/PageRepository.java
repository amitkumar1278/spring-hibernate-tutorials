package com.spring.sboot.repositories.one2many;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.domains.one2many.Book;
import com.spring.sboot.domains.one2many.Page;

public interface PageRepository extends CrudRepository<Page, Long> {
	
	List<Page> findByBook(Book book, Sort sort);

}
