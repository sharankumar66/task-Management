package com.project.taskManagement.resHelper;

import com.project.taskManagement.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth_Response {

	private String jwt;
	private String refreshToken;
	private User user;
	private String message;

}
