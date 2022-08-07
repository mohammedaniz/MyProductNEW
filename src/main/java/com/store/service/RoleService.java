package com.store.service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.PermissionE;
import com.store.entities.RoleE;
import com.store.models.Role;
import com.store.models.dto.RoleDTO;
import com.store.repository.PermissionRepository;
import com.store.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	public RoleDTO saveNewUserService(Role role) {
		RoleE re = new RoleE(0, role.getRoleName());
		if(!role.getPermissions().isEmpty()) {
			Set<Long> permissions = role.getPermissions();
			Set<PermissionE> pp = new LinkedHashSet<>();
			Iterable<PermissionE> savedPermissions = permissionRepository.findAllById(permissions);
					Iterator<PermissionE> iter = savedPermissions.iterator();
					while(iter.hasNext()){
						PermissionE p = iter.next();
						p.getRoles().add(re);
						pp.add(p);
					}
					re.setPermissions(pp);
		}
		
		RoleE savedRole = roleRepository.save(re);
		return this.buildRoleDTO(savedRole);
	}
	
	
	public RoleDTO deleteRoleByRoleIdService(long roleId) {
		Optional<RoleE> isRoleExist = roleRepository.findById(roleId);
		if(isRoleExist.isPresent()) {
			RoleE rr = isRoleExist.get();
			Set<PermissionE> perm = rr.getPermissions();
			Iterator<PermissionE> permIterator = perm.iterator();
			while(permIterator.hasNext()) {
				PermissionE pe = permIterator.next();
				pe.getRoles().remove(rr);
			}
			roleRepository.delete(rr);
		}
		
		return new RoleDTO(roleId, null);
	}
	
	public RoleDTO getRoleByRoleIdService(long roleId) {
		Optional<RoleE> savedRole = roleRepository.findById(roleId);
		if(savedRole.isPresent()) {
			RoleE role = savedRole.get();
			return this.buildRoleDTO(role);
		}
		return null;
	}
	
	public RoleDTO buildRoleDTO(RoleE role) {
		RoleDTO dto = new RoleDTO(role.getRoleId(), role.getRoleName());
		
		return dto;
	}
}
