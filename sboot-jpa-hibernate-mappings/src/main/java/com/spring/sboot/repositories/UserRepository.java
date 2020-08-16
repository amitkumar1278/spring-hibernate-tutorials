package com.spring.sboot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.domains.one2one.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
