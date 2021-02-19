package com.talan.pfemanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talan.pfemanager.dto.RoleDTO;
import com.talan.pfemanager.dto.SubjectDTO;
import com.talan.pfemanager.dto.UserDTO;
import com.talan.pfemanager.entity.Technology;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PfemanagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@Transactional
@Rollback(true)
public class SubjectControllerTest {
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
	public void testGetAllsubjects() throws Exception {
		String token;
		token = getToken();
		mvc.perform(get("/subjects").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testGetSubjectById() throws Exception {
		String token;
		token = getToken();
		mvc.perform(
				get("/subjects/1").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	public void testDeleteAllSubjects() throws Exception {
		String token;
		token = getToken();
		mvc.perform(MockMvcRequestBuilders.delete("/subjects").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}

	@Test
	public void testAddSubject() throws Exception {
		String token;
		token = getToken();
		Set<Technology> technologies = new HashSet<Technology>();
		RoleDTO role = new RoleDTO(2, "COLLAB");
		UserDTO newUser = new UserDTO("test@test.com", "123456", "test", "test", "test", role);
		Date publicationDate = new Date(2021, 01, 11);
		Date startDate = new Date(2021, 01, 20);
		Date endDate = new Date(2021, 05, 11);
		SubjectDTO subject = new SubjectDTO("new subject", "new subject description", publicationDate, startDate,
				endDate, technologies, newUser);
		mvc.perform(MockMvcRequestBuilders.post("/subjects").content(asJsonString(subject))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteSubjectById() throws Exception {
		String token;
		token = getToken();
		mvc.perform(MockMvcRequestBuilders.delete("/subjects/{id}", 1).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}

	@Test
	public void testSearch() throws Exception {
		String token;
		token = getToken();
		mvc.perform(MockMvcRequestBuilders.post("/subjects/search/{ids}", 1, 2).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}
}
