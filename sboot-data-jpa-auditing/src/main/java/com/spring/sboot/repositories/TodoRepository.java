package com.spring.sboot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.model.Todo;


public interface TodoRepository extends CrudRepository<Todo, Long> {

}
