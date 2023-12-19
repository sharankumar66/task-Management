package com.project.taskManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.taskManagement.dto.User;
import com.project.taskManagement.service.User_Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {
	@Autowired
	User_Service userService;

	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User users, @PathVariable("userId") Integer userId) {
		User user = userService.updateUser(users, userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer UserId) {
		User user = userService.getUserById(UserId);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
}
