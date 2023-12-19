package com.project.taskManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.taskManagement.dto.User;
import com.project.taskManagement.exception.UserException;
import com.project.taskManagement.reqHelper.U_LoginRequest;
import com.project.taskManagement.resHelper.Auth_Response;
import com.project.taskManagement.resHelper.Login_Response;
import com.project.taskManagement.service.Auth_Service;
import com.project.taskManagement.service.User_Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {
	@Autowired
	Auth_Service authService;
	@Autowired
	User_Service userService;

	@PostMapping("/signup")
	public ResponseEntity<Auth_Response> signup(@Valid @RequestBody User req) throws UserException {

		return new ResponseEntity<Auth_Response>(authService.signup(req), HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<Login_Response> signin(@RequestBody U_LoginRequest req) throws UserException {

		return new ResponseEntity<Login_Response>(authService.signin(req), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

}
