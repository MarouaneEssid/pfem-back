package com.talan.pfemanager;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talan.pfemanager.dto.RoleDTO;
import com.talan.pfemanager.dto.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PfemanagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@Transactional
@Rollback(true)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	public static String asJsonString(final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}

	public String getToken() throws Exception {
		String username = "khalil@hamdi.com";
		String password = "123456789";

		String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isOk()).andReturn();

		String response = result.getResponse().getContentAsString();
		response = response.replace("{\"access_token\": \"", "");
		String token = response.replace("'}", "");
		token = token.replace("{\"jwt\":\"", "");
		token = token.replace("\"}", "");
		return token;
	}

	@Test
	public void testGetAllUsers() throws Exception {
		String token;
		token = getToken();

		mvc.perform(
				get("/users/all").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testGetUser() throws Exception {

		String token;
		token = getToken();
		mvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	public void testUpdateUser() throws Exception {
		String token;
		token = getToken();

		RoleDTO role = new RoleDTO(1, "ADMIN");
		UserDTO user = new UserDTO("test@test.com", "123456", "test", "test", "test", role);

		mvc.perform(MockMvcRequestBuilders.put("/users/{id}", 2).content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token)).andExpect(status().isOk());

	}

	@Test
	public void testDeleteUser() throws Exception {
		String token;
		token = getToken();
		mvc.perform(MockMvcRequestBuilders.delete("/users/{id}", 2).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteAllUsers() throws Exception {
		String token;
		token = getToken();
		mvc.perform(MockMvcRequestBuilders.delete("/users").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetUserByRoleId() throws Exception {

		String token;
		token = getToken();
		mvc.perform(
				get("/users/role/1").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void createUser() throws Exception {

		RoleDTO role = new RoleDTO(1, "ADMIN");
		UserDTO user = new UserDTO("test@test.com", "123456", "test", "test", "test", role);

		mvc.perform(MockMvcRequestBuilders.post("/register").header("Authorization", "Bearer " + getToken())
				.content(asJsonString(user)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdateUserPassword() throws Exception {
		String token;
		token = getToken();

		String password;
		password="123456789";

		mvc.perform(MockMvcRequestBuilders.put("/users/password/{id}", 2).content(password)
				.header("Authorization", "Bearer " + token)).andExpect(status().isOk());

	}
	

}
