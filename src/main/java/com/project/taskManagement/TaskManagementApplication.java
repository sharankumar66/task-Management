package com.project.taskManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.taskManagement.dto.Role;
import com.project.taskManagement.dto.User;
import com.project.taskManagement.repository.UserRepository;

@SpringBootApplication
public class TaskManagementApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if (adminAccount == null) {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setName("admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin@123"));
			user.setRole(Role.ADMIN);
			userRepository.save(user);

		}
	}

}
