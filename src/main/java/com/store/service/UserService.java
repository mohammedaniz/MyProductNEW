package com.store.service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.RoleE;
import com.store.entities.UserE;
import com.store.exceptions.UserAlreadyExistException;
import com.store.models.User;
import com.store.models.dto.RoleDTO;
import com.store.models.dto.UserDTO;
import com.store.repository.RoleRepository;
import com.store.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	public UserDTO saveNewUserDetailService(User user) throws UserAlreadyExistException {
		Optional<UserE> isUserExist = userRepository.findByEmailId(user.getEmailId());
		if(isUserExist.isPresent()) {
			throw new UserAlreadyExistException("User By Email Id " + user.getEmailId() + " Already Exists!!!");
		}
		UserE ue = this.buildUserEntityService(user);
		UserE su = userRepository.save(ue);
		return this.buildUserDTOService(su);
	}
	
	public UserDTO getUserByUserId(long userId) {
		if(userId > 0) {
			Optional<UserE> isUser = userRepository.findById(userId);
			if(isUser.isPresent()) {
				UserE user = isUser.get();
				UserDTO dto = this.buildUserDTOService(user);
				return dto;
			}else {
				throw new RuntimeException("User Does Not Exists!!");
			}
		}else {
			throw new RuntimeException("User Id Should be greater than 0");
		}
	}
	
	public Set<UserDTO> getAllSavedUserService() {
		Set<UserDTO> users = new LinkedHashSet<>();
			Iterator<UserE> isUser = userRepository.findAll().iterator();
			while(isUser.hasNext()) {
				UserE user = isUser.next();
				UserDTO dto = this.buildUserDTOService(user);
				 users.add(dto);
			}
			return users;
		
	}
	
	public UserE buildUserEntityService(User user) {
		if(user != null) {
			Set<RoleE> userRoles = new LinkedHashSet<>();
			Iterable<RoleE> roles = roleRepository.findAllById(user.getRoles());
			Iterator<RoleE> roleIterator = roles.iterator();
			while(roleIterator.hasNext()) {
				RoleE role = roleIterator.next();
				userRoles.add(role);
			}
			
			UserE u = new UserE(0, user.getGender(), user.getEmailId(), user.getPassword(),userRoles);
			return u;
		}else {
			throw new NullPointerException("User Request Object is null ");
		}
	} 
	
	public UserDTO buildUserDTOService(UserE user) {
		if(user != null) {
			Set<RoleDTO> dtos = new LinkedHashSet<>();
			if(user.getRoles() != null && !user.getRoles().isEmpty()) {
				Iterator<RoleE> roles = user.getRoles().iterator();
				while(roles.hasNext()) {
					RoleDTO dto = roleService.buildRoleDTO(roles.next());
					dtos.add(dto);
				}
				
			}
			
			UserDTO dto = new UserDTO(user.getUserId(), user.getUserName(), user.getEmailId(), dtos);
			return dto;
		}else {
			throw new NullPointerException("User Request Object is null ");
		}
	}
} 
