package com.store.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenManager {
	
	String jwtSecret = "testTokenSecret";
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
			.setClaims(null)
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))
			.signWith(SignatureAlgorithm.HS256, jwtSecret)
			.compact();
	}
	
	public Boolean validateJwtToken(String token, UserDetails userDetails) { 
	      String username = getUsernameFromToken(token); 
	      Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	      Boolean isTokenExpired = claims.getExpiration().before(new Date()); 
	      return (username.equals(userDetails.getUsername()) && !isTokenExpired); 
	   } 
	   public String getUsernameFromToken(String token) {
	      final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody(); 
	      return claims.getSubject(); 
	   } 

}
