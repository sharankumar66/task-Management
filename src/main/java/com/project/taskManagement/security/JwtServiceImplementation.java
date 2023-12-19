package com.project.taskManagement.security;

import java.security.Key;

import java.util.Date;

import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtServiceImplementation implements JwtService {

	private String secret = "bbb9a4edec5dc9834560aaa16030fe8917741a714b1e0a71681db056e9bed99b";
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	// retrieve username from jwt token
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) {

		return Jwts.builder()

				.setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * JWT_TOKEN_VALIDITY))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	// generate token for user
	public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {

		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * JWT_TOKEN_VALIDITY))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// for retrieveing any information from token we will need the secret key
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}


}
