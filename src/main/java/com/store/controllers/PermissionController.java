package com.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.models.Permission;
import com.store.models.dto.PermissionDTO;
import com.store.service.PermissionService;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@PreAuthorize("hasAnyAuthority('WRITE_PERMISSION')")
	@RequestMapping(value = "save" , method = RequestMethod.POST)
	public ResponseEntity<Object> saveNewPermisson(@RequestBody Permission permission) {
		PermissionDTO dto = permissionService.saveNewPermissionService(permission);
		return new ResponseEntity<Object>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('READ_PERMISSION')")
	@RequestMapping(value = "{permissionid}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPermissionDetailsByRoleIdController(@PathVariable("permissionid") long permissionid) {
		PermissionDTO dto = permissionService.getPermissionDetailByPermissionIdService(permissionid);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
//	@RequestMapping(value = "{permissionid}", method = RequestMethod.DELETE)
//	public ResponseEntity<Object> deleteRoleDetailsByRoleIdController(@PathVariable("roleid") long roleId) {
//		
//		return new ResponseEntity<>(dto,HttpStatus.OK);
//	}
}
