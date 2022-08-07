package com.store.models.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {

	private long permissionId;
	
	private String permissionName;
	
	private Set<RoleDTO> roles = new LinkedHashSet<>();
}
