package com.store.models.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private long userId;
	
	private String username;
	
	private String emailId;
	
	private Set<RoleDTO> roles = new HashSet<>();

}
