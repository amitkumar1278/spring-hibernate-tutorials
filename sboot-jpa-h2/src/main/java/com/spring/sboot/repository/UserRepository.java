package com.spring.sboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
