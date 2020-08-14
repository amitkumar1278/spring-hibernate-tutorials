package com.spring.sboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sboot.model.User;
import com.spring.sboot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * getting all users record by using the method findaAll() of CrudRepository
	 */
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(user1 -> users.add(user1));
		return users;
	}

	/**
	 * getting a specific record by using the method findById() of CrudRepository
	 */
	public User getUSerById(int userID) {
		return userRepository.findById(userID).get();
	}

	/**
	 * saving a specific record by using the method save() of CrudRepository
	 */
	public void saveOrUpdate(User user) {
		userRepository.save(user);
	}

	/**
	 * deleting a specific record by using the method deleteById() of CrudRepository
	 */
	public void delete(int userID) {
		userRepository.deleteById(userID);
	}

	/**
	 * updating a record
	 */
	public void update(User user, int userID) {
		userRepository.save(user);
	}

}
