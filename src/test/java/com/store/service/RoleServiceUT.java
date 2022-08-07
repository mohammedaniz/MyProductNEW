package com.store.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.store.entities.RoleE;
import com.store.models.Role;
import com.store.models.dto.RoleDTO;
import com.store.repository.RoleRepository;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceUT {
	
	@Mock
	private RoleRepository roleRepository;
	
	@InjectMocks
	private RoleService roleService;
	
	//@Test
	public void shouldTestSaveNewUserService_whenValidUserIsPassed_receiveSuccess() {
		RoleE r = this.newRole(0,"ROLE_USER");
		RoleE s = this.newRole(1, "ROLE_USER");
		Role rr = new Role(1, "ROLE_USER",null);
		when(roleRepository.save(r)).thenReturn(s);
		RoleDTO response =roleService.saveNewUserService(rr);
		Assertions.assertThat(response.getRoleId()).isEqualTo(2);
	}
	
	public RoleE newRole(long id,String name) {
		return new RoleE(id,name,null);
	}
	
	@Test
	public void testGetRoleByRoleIdService_whenValidRoleDTO_receiveRoleDTO() {
		long roleId = 1l;
		RoleE role = new RoleE();
		role.setRoleId(roleId);
		role.setRoleName("ROLE_USER");
		when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
		RoleDTO dto = roleService.getRoleByRoleIdService(roleId);
		Assertions.assertThat(dto.getRoleName()).isEqualTo(role.getRoleName());
	}
	
	@Test
	public void testGetRoleByRoleIdService_whenInvalidNegativeRoleIDPassed_receiveRoleDTO() {
		long roleId = -1l;
		RoleE role = new RoleE();
		role.setRoleId(roleId);
		role.setRoleName("ROLE_USER");
		RoleDTO dto = roleService.getRoleByRoleIdService(roleId);
		Assertions.assertThat(dto).isNull();
	}
}
