package com.store.service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.PermissionE;
import com.store.entities.RoleE;
import com.store.models.Permission;
import com.store.models.dto.PermissionDTO;
import com.store.models.dto.RoleDTO;
import com.store.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private RoleService roleService;
	
	public PermissionDTO saveNewPermissionService(Permission permission) {
		PermissionE p = this.buildPermissionEntity(permission);
		PermissionE rp = permissionRepository.save(p);
		return this.buildPermissionDTO(rp);
	}
	
	public PermissionDTO getPermissionDetailByPermissionIdService(long permissionId) {
		if(permissionId > 0) {
			Optional<PermissionE> isPermissionExist = permissionRepository.findById(permissionId);
			if(isPermissionExist.isPresent()) {
				PermissionE p = isPermissionExist.get();
				PermissionDTO dto = this.buildPermissionDTO(p);
				return dto;
			}else {
				throw new RuntimeException("Unable to Build Permission DTO as Entity is Null");
			}
		}else {
			throw new RuntimeException("Unable to Build Permission DTO as Entity is Null");
		}
	}
	
	public PermissionE buildPermissionEntity(Permission permission) {
		return new PermissionE(permission.getPermissionId(), permission.getPermissionName(), null);
	}
	
	public PermissionDTO buildPermissionDTO(PermissionE permission) {
		Set<RoleDTO> roles = new LinkedHashSet<>();
		if(permission != null) {
			if(permission.getRoles() != null && !permission.getRoles().isEmpty() ) {
				Iterator<RoleE> roleIterator = permission.getRoles().iterator();
				 while(roleIterator.hasNext()) {
					 RoleE role = roleIterator.next();
					 RoleDTO dto = roleService.buildRoleDTO(role);
					 roles.add(dto);
				 } 
			}
			PermissionDTO permissionDTO = new PermissionDTO(permission.getPermissionId(), permission.getPermissionName(), roles);
			return permissionDTO;
		}else {
			throw new RuntimeException("Unable to Build Permission DTO as Entity is Null");
		}
	}
}
