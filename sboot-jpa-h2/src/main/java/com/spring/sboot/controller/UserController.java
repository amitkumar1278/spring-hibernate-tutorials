package com.spring.sboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sboot.model.User;
import com.spring.sboot.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userServices;

	/**
	 * creating post mapping that post the user detail in the database
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/user")
	private int saveUser(@RequestBody User user) {
		userServices.saveOrUpdate(user);
		return user.getId();
	}

	/**
	 * creating a get mapping that retrieves all the users detail from the database
	 * 
	 * @return
	 */
	@GetMapping("/users")
	private List<User> getAllUsers() {
		return userServices.getAllUsers();
	}

	/**
	 * creating a get mapping that retrieves the detail of a specific user
	 * 
	 * @param userID
	 * @return
	 */
	@GetMapping("/user/{userID}")
	private User getUser(@PathVariable("userID") int userID) {
		return userServices.getUSerById(userID);
	}

	/**
	 * creating put mapping that updates the user detail
	 * 
	 * @param user
	 * @return
	 */
	@PutMapping("/user")
	private User update(@RequestBody User user) {
		userServices.saveOrUpdate(user);
		return user;
	}

	/**
	 * creating a delete mapping that deletes a specified user
	 * 
	 * @param userID
	 */
	@DeleteMapping("/user/{userID}")
	private void deleteUser(@PathVariable("userID") int userID) {
		userServices.delete(userID);
	}

}
