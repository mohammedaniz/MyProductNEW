package com.store.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTFilter extends OncePerRequestFilter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JWTTokenManager tokenManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String tokenHeader = request.getHeader("Authorization");
		System.out.println("Token Header " + tokenHeader);
		String username = null;
		String token = null;
		if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			 token  = tokenHeader.substring(7);
			username  = tokenManager.getUsernameFromToken(token);
			
			
		}
		System.err.println(token);
		System.err.println(username);
		
//		UserDetails userDetails  = userDetailsService.loadUserByUsername(username);
//		System.out.println(tokenManager.validateJwtToken(token, userDetails));
//		System.out.println(userDetails.getAuthorities());
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			System.out.println("Inside Not Set to Authentication ");
			UserDetails userDetails  = userDetailsService.loadUserByUsername(username);
			if(tokenManager.validateJwtToken(token, userDetails)) {
				System.out.println("Valid JWT ");
				UsernamePasswordAuthenticationToken
	            authenticationToken = new UsernamePasswordAuthenticationToken(
	            userDetails, null,
	            userDetails.getAuthorities());
	            authenticationToken.setDetails(new
	            WebAuthenticationDetailsSource().buildDetails(request));
	            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
			}
		}
		filterChain.doFilter(request, response);
	}

}
