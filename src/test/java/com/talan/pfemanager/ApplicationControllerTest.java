package com.talan.pfemanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talan.pfemanager.dto.ApplicationDTO;
import com.talan.pfemanager.entity.Application;
import com.talan.pfemanager.entity.Role;
import com.talan.pfemanager.entity.Subject;
import com.talan.pfemanager.entity.Technology;
import com.talan.pfemanager.entity.User;
import com.talan.pfemanager.helper.ApplicationHelper;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PfemanagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@Transactional
@Rollback(true)
public class ApplicationControllerTest extends TestCase {
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
	public void getApplicationsByUserIdAPI() throws Exception {
		String token;
		token = getToken();
		mvc.perform(get("/applications/user/3").contentType(MediaType.APPLICATION_JSON).header("Authorization",
				"Bearer " + token)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

	}
	
	@Test
	public void applicationDelete() throws Exception {
		String token;
		token = getToken();
		mvc.perform(MockMvcRequestBuilders.delete("/applications/{id}", 1).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());

	}
	
	@Test
	public void testGetApplicationsByCollab() throws Exception {
		String token;
		token = getToken();
		mvc.perform(get("/applications/collab/{id}", 1).contentType(MediaType.APPLICATION_JSON).header("Authorization",
				"Bearer " + token)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

	}
	@Test
	public void testApplicationEnityAndDTO() {
		Set<Technology> technologies = new HashSet<Technology>();
		Role role = new Role(1, "ADMIN");
		User user = new User("test@test.com", "123456", "test", "test", "test", role);
		Date publicationDate = new Date(2021, 01, 11);
		Date startDate = new Date(2021, 01, 20);
		Date endDate = new Date(2021, 05, 11);
		Subject subject = new Subject("new subject", "new subject description", publicationDate, startDate,
				endDate, technologies, user);		Application application = new Application("coverLetter", "resume", user, subject);
		ApplicationDTO applicationDTO= new ApplicationDTO();
		applicationDTO=ApplicationHelper.entityToDto(application);
		application= ApplicationHelper.dtoToEntity(applicationDTO);
		assertEquals(application.getResume(),applicationDTO.getResume()); 
	}
	
	@Test
	public void deleteApplications() throws Exception {
		String token;
		token = getToken();
		mvc.perform(MockMvcRequestBuilders.delete("/applications").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}
	
}
