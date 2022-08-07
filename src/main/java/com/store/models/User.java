package com.store.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@NotBlank(message = "EmailId is mandatory")
	private String emailId;
	
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	private String gender;
	
	private String username;
	
	private Set<Long> roles = new HashSet<>();
}
