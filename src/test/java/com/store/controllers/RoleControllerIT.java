package com.store.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.store.models.Role;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class RoleControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldTestSaveNewRole_whenValidRoleIsPassed_receiveOk() throws Exception {
		mockMvc.perform(post("/api/v1/roles/save")
					.contentType(MediaType.APPLICATION_JSON)
					.content(this.roleObjectInString(this.createNewRole(0, "ROLE_USER"))))
		.andExpect(status().is(200));
	}
	
	public Role createNewRole(long id, String roleName) {
		return new Role(0, roleName,null);
	}
	
	public String roleObjectInString(Role role) throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(role);
	}
	
	
}
