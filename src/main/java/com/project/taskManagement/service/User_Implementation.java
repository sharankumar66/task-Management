package com.project.taskManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.taskManagement.dto.Role;
import com.project.taskManagement.dto.User;
import com.project.taskManagement.exception.SourceNotFoundException;
import com.project.taskManagement.repository.UserRepository;

@Service
public class User_Implementation implements User_Service{
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(User users) {
		return userRepository.save(users);
	}

	@Override
	public User updateUser(User users, Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new SourceNotFoundException("User", "Id", userId));
		user.setEmail(users.getEmail());
		user.setName(users.getName());
		user.setPassword(users.getPassword());
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(() -> new SourceNotFoundException("User", "Id", userId));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new SourceNotFoundException("User", "Id", userId));
		userRepository.delete(user);
	}

	@Override
	public User registerNewUser(User user) {

		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		user.setRole(Role.USER);
		User newUser = this.userRepository.save(user);
		return newUser;
	}

}
