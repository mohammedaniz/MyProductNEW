package com.store.service;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.store.entities.PermissionE;
import com.store.models.Permission;
import com.store.models.dto.PermissionDTO;
import com.store.repository.PermissionRepository;

@RunWith(MockitoJUnitRunner.class)
public class PermissionServiceUT {

	@Mock
	private PermissionRepository permissionRepository;
	
	@Spy
	@InjectMocks
	private PermissionService permissionService;
	
	
	public Permission permission;
	
	public PermissionDTO dto;
	
	public PermissionE entity; 
	
	@Before
	public void setUp() {
		this.permission = new Permission(1l, "WRITE_NOTHING");
		this.entity = this.permissionEntity(permission);
		this.dto = this.permissionDTO(entity);
	}
	
	@Test
	public void shouldTestSaveNewPermissionService_whenValidPermissionIsPassed_receiveSavedPermissionDTO() {
		PermissionE e = new PermissionE(this.entity.getPermissionId(), this.entity.getPermissionName(), this.entity.getRoles());
		doReturn(this.entity).when(this.permissionService).buildPermissionEntity(this.permission);
		when(permissionRepository.save(this.entity)).thenReturn(e);
		PermissionDTO actual = this.permissionService.saveNewPermissionService(this.permission);
		assertThat(actual.getPermissionId()).isEqualTo(this.dto.getPermissionId());
		assertThat(actual.getPermissionName()).isEqualTo(this.dto.getPermissionName());
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldTestSaveNewPermissionService_whenNullPermissionObjectIsPassed_receiveRuntimeException() {
		
		this.permissionService.saveNewPermissionService(null);
	}
	
	@Test
	public void shouldTestGetPermissionDetailByPermissionIdService_whenValidPermissionIdIsPassed_receivePermissionDTO() {
		when(this.permissionRepository.findById(this.permission.getPermissionId())).thenReturn(Optional.of(this.entity));
		PermissionDTO dto = this.permissionService.getPermissionDetailByPermissionIdService(this.permission.getPermissionId());
		assertThat(dto.getPermissionName()).isEqualTo(dto.getPermissionName());
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldTestGetPermissionDetailByPermissionIdService_whenInValidPermissionIdIsPassed_receiveException() {
		when(this.permissionRepository.findById(this.permission.getPermissionId())).thenReturn(Optional.empty());
		this.permissionService.getPermissionDetailByPermissionIdService(this.permission.getPermissionId());
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldTestGetPermissionDetailByPermissionIdService_whenNegativePermissionIdIsPassed_receiveException() {
		this.permissionService.getPermissionDetailByPermissionIdService(-1l);
	}
	
	public PermissionE permissionEntity(Permission permission) {
		return new PermissionE(permission.getPermissionId(), permission.getPermissionName(), null);
	}
	
	public PermissionDTO permissionDTO(PermissionE permission) {
		return new PermissionDTO(permission.getPermissionId(), permission.getPermissionName(), null);
	}
}
