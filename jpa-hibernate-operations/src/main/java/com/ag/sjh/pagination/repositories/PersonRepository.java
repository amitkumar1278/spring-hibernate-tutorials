package com.ag.sjh.pagination.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ag.sjh.pagination.domains.Person;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	Page<Person> findByLastName(String lastName, Pageable pageable);

	Slice<Person> findByAgeBetween(int start, int end, Pageable pageable);

}
