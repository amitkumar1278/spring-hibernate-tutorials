package com.spring.sboot.repositories.one2one;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.domains.one2one.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByEmail(String email);

}
