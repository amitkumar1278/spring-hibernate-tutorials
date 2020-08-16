package com.spring.sboot.repositories.one2many;

import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.domains.one2many.Page;

public interface PageRepository extends CrudRepository<Page, Long> {

}
