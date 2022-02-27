package com.ganit.chat.controller;

import java.util.List;

import com.ganit.chat.exception.EntityNotFoundException;
import com.ganit.chat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ganit.chat.service.UserService;


@RestController
public class UserController {

	
	@Autowired
	UserService userService;

	@GetMapping("/users")
	@ResponseBody
	public List<User> getAllUsers() {
		return userService.findAll();
	}

	@PostMapping("/user")
	public void addUser(@Validated @RequestBody User user) {
		
		userService.updateUser(user);
	}
	
	@PostMapping("/user/{nickName}")
	public void addUser(@Validated @PathVariable(required = true)String nickName) {
		
		userService.createUserByNickName(nickName);
	}
	
	
	@GetMapping("/user/{userId}")
	@ResponseBody
	public User getUserByID(@Validated @PathVariable(required = true)long userId) {
		 
		User user=userService.findUserByID(userId);
		if(user==null) {
			throw new EntityNotFoundException("User not found!");
		}
		return user;
	}	

}
