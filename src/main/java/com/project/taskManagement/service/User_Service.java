package com.project.taskManagement.service;

import java.util.List;

import com.project.taskManagement.dto.User;

import jakarta.validation.Valid;

public interface User_Service {
	public User createUser(User users);

	public User updateUser(User users, Integer userId);

	public User getUserById(Integer userId);

	public List<User> getAllUsers();

	public void deleteUser(Integer userId);

	User registerNewUser(@Valid User userDto);
}
