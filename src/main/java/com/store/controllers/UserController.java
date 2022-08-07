package com.store.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.exceptions.UserAlreadyExistException;
import com.store.models.Response;
import com.store.models.User;
import com.store.models.dto.UserDTO;
import com.store.service.UserService;
import com.store.utils.CustomBeanValidationUtil;
import com.store.utils.CustomHttpStatusBuilder;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomBeanValidationUtil beanValidator;
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<Object> saveNewUserDetailsController(@Valid @RequestBody User user,BindingResult errors) throws UserAlreadyExistException {
		if(errors.hasErrors()) {
			Response response = beanValidator.beanValidation(errors, "Please Provide Correct Value For User Fields");
			return CustomHttpStatusBuilder.buildResponseEntity(response, null);
		}
		UserDTO dto = userService.saveNewUserDetailService(user);
		return new ResponseEntity<Object>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{userid}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserDetailsByUserIdController(@PathVariable("userid") long userId) {
		UserDTO dto = userService.getUserByUserId(userId);
		return new ResponseEntity<Object>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllUserDetailsController() {
		Set<UserDTO> users = userService.getAllSavedUserService();
		return new ResponseEntity<Object>(users, HttpStatus.OK);
	}

}
