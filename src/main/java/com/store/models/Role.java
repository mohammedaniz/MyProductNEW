package com.store.models;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	private long roleId;
	
	private String roleName;
	
	private Set<Long> permissions = new HashSet<>();
	
}
