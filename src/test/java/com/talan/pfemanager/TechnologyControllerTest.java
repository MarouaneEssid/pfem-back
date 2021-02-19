package com.talan.pfemanager;

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
import com.talan.pfemanager.dto.TechnologyDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PfemanagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test") 
@Transactional
@Rollback(true)
public class TechnologyControllerTest {
	
	@Autowired
	private MockMvc mvc;

	public static String asJsonString(final Object obj) throws JsonProcessingException {
			return new ObjectMapper().writeValueAsString(obj);
		
	}
	
	public String getToken () throws Exception {
		String username = "khalil@hamdi.com";
        String password = "123456789";

        String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();
        
        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"access_token\": \"", "");
        String token = response.replace("'}", "");
        token=token.replace("{\"jwt\":\"", "");
        token=token.replace("\"}","");
        return token;
	}

	@Test
	public void testGetAllTechnologies() throws Exception {
		String token;
		token= getToken();
		mvc.perform(get("/technologies/all").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testGetTechnology() throws Exception {
		String token;
		token= getToken();
		mvc.perform(get("/technologies/1").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void testAddTechnology() throws Exception {
		String token;
		token= getToken();
		TechnologyDTO technology = new TechnologyDTO("java");
		mvc.perform(MockMvcRequestBuilders.post("/technologies").content(asJsonString(technology))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteTechnology() throws Exception {
		String token;
		token= getToken();
		mvc.perform(MockMvcRequestBuilders.delete("/technologies/{id}", 1).header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteAllTechnologies() throws Exception {
		String token;
		token= getToken();
		mvc.perform(MockMvcRequestBuilders.delete("/technologies").header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}


}
