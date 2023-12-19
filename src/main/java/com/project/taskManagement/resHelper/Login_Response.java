package com.project.taskManagement.resHelper;

import com.project.taskManagement.dto.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login_Response {
	private Integer id;
	private String name;
	private String email;
	private Role role;
	private String jwt;
}
