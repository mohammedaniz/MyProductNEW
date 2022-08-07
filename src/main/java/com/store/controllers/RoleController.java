package com.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.models.Role;
import com.store.models.dto.RoleDTO;
import com.store.service.RoleService;

@RestController
@RequestMapping(value = "/api/v1/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<Object> saveNewRoleDetailsController(@RequestBody Role role) {
		RoleDTO dto = roleService.saveNewUserService(role);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@RequestMapping(value = "{roleid}", method = RequestMethod.GET)
	public ResponseEntity<Object> getRoleDetailsByRoleIdController(@PathVariable("roleid") long roleId) {
		RoleDTO dto = roleService.getRoleByRoleIdService(roleId);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@RequestMapping(value = "{roleid}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteRoleDetailsByRoleIdController(@PathVariable("roleid") long roleId) {
		RoleDTO dto = roleService.deleteRoleByRoleIdService(roleId);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
}
