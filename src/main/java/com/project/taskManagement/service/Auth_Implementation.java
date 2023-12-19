package com.project.taskManagement.service;

import java.util.Optional;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.taskManagement.dto.Role;
import com.project.taskManagement.dto.User;
import com.project.taskManagement.exception.SourceNotFoundException;
import com.project.taskManagement.exception.UserException;
import com.project.taskManagement.repository.UserRepository;
import com.project.taskManagement.reqHelper.U_LoginRequest;
import com.project.taskManagement.resHelper.API_Response;
import com.project.taskManagement.resHelper.Auth_Response;
import com.project.taskManagement.resHelper.Login_Response;
import com.project.taskManagement.security.JwtService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class Auth_Implementation implements Auth_Service {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	public Auth_Response signup(User registerRequest) {
		Optional<User> isEmailExist = userRepository.findByEmail(registerRequest.getEmail());

		if (isEmailExist.isPresent()) {
			throw new UserException("Email is Already used with other Account");

		}

		registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

		registerRequest.setRole(Role.USER);

		User savedUser = userRepository.save(registerRequest);
		Auth_Response authResponse = new Auth_Response();
//		
//	var jwt=jwtService.generateToken(user);
//	var rjwt=jwtService.generateRefreshToken(new HashMap<String, Object>(), user);
//		authResponse.setJwt(jwt);
//		authResponse.setRefreshToken(rjwt);
		savedUser.setPassword(null);
		authResponse.setUser(savedUser);
		authResponse.setMessage("SignUp Success");
		return authResponse;
	}

	@Override
	public Login_Response signin(U_LoginRequest loginRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		User user = userRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		var jwt = jwtService.generateToken(user);
//		var rjwt=jwtService.generateRefreshToken(new HashMap<String, Object>(), user);
		Login_Response loginResponse = new Login_Response();
		loginResponse.setJwt(jwt);
		loginResponse.setEmail(user.getEmail());
		loginResponse.setId(user.getId());
		loginResponse.setName(user.getName());
		loginResponse.setRole(user.getRole());

		return loginResponse;
	}

	@Override
	public API_Response updatePassword(Integer userId, String password) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new SourceNotFoundException("User", "Id", userId));
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		return new API_Response("Password Updated Successfully", true);
	}

}
