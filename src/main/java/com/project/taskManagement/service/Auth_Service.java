package com.project.taskManagement.service;

import com.project.taskManagement.dto.User;
import com.project.taskManagement.reqHelper.U_LoginRequest;
import com.project.taskManagement.resHelper.API_Response;
import com.project.taskManagement.resHelper.Auth_Response;
import com.project.taskManagement.resHelper.Login_Response;

public interface Auth_Service {
	Auth_Response signup(User registerRequest);

	Login_Response signin(U_LoginRequest loginRequest);

	API_Response updatePassword(Integer userId, String password);
}
