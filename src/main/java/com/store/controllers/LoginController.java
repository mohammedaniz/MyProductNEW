package com.store.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.models.Login;
import com.store.models.dto.LoginDTO;
import com.store.security.CustomUserDetailsService;
import com.store.security.JWTTokenManager;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
	@Autowired
	   private CustomUserDetailsService userDetailsService;
	
	   @Autowired
	   private JWTTokenManager tokenManager;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Login login) {
		try {
		//	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmailId(), login.getPassword()));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmailId());
	      final String jwtToken = tokenManager.generateToken(userDetails);
	      return ResponseEntity.ok(new LoginDTO(jwtToken));
	}
}
