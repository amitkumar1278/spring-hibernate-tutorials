package com.ag.sjh.repositories.one2one;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ag.sjh.domains.one2one.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByEmail(String email);

}
