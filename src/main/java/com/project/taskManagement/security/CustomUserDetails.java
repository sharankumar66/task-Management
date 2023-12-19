package com.project.taskManagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.taskManagement.dto.User;
import com.project.taskManagement.exception.SourceNotFoundException;
import com.project.taskManagement.repository.UserRepository;

@Service
public class CustomUserDetails implements UserDetailsService{
	
//	@Autowired
	 private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// loading user from database by username
		User user = this.repository.findByEmail(username)
				.orElseThrow(() -> new SourceNotFoundException("User ", " email : " + username, 0));

		return user;
	}
	
}
