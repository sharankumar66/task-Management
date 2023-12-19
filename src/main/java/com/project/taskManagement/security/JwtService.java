package com.project.taskManagement.security;

import java.util.Map;


import java.util.function.Function;

import org.springframework.stereotype.Repository;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;


public interface JwtService {

	String extractUserName(String token);

	String generateToken(UserDetails userDetails);

	String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);

	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);

	Boolean validateToken(String token, UserDetails userDetails);

}
