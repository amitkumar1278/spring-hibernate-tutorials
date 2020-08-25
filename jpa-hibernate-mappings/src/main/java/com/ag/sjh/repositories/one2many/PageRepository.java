package com.ag.sjh.repositories.one2many;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ag.sjh.domains.one2many.Book;
import com.ag.sjh.domains.one2many.Page;

public interface PageRepository extends CrudRepository<Page, Long> {
	
	List<Page> findByBook(Book book, Sort sort);

}
