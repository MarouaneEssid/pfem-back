package com.talan.pfemanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.talan.pfemanager.entity.Role;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PfemanagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test") 
@Transactional
@Rollback(true)
public class RoleControllerTest {

    @Autowired
    private MockMvc mock;

    private String toJson(Object obj) {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException ex) { }
        return json;
    }

    public String getToken() throws Exception {
        String username = "khalil@hamdi.com";
        String password = "123456789";

        String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

        MvcResult result = mock.perform(MockMvcRequestBuilders.post("/authenticate").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"access_token\": \"", "");
        String token = response.replace("'}", "");
        token = token.replace("{\"jwt\":\"", "");
        token = token.replace("\"}", "");
        return token;
    }

    @Test
    public void getRole() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/roles/{id}", 1)
                .header("Authorization", "Bearer " + getToken()))
                .andExpect(status().isOk());
        mock.perform(MockMvcRequestBuilders.get("/roles/{id}", 2)
                .header("Authorization", "Bearer " + getToken()))
                .andExpect(status().isOk());
    }

   @Test
   public void deleteRole() throws Exception {
        mock.perform(MockMvcRequestBuilders.delete("/roles/{id}", 1)
               .header("Authorization", "Bearer " + getToken()))
               .andExpect(status().isOk());
    }

    @Test
    public void createRole() throws Exception {
        Role roleDto = new Role();
        roleDto.setId(3);
        roleDto.setName("MOCK_ROLE");
        mock.perform(MockMvcRequestBuilders.post("/roles")
                .header("Authorization", "Bearer " + getToken())
                .content(toJson(roleDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
	@Test
	public void getAllRoles() throws Exception {
		String token;
		token= getToken();
		mock.perform(get("/roles").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
	@Test
	public void UpdateRole() throws Exception {
		String token;
		token = getToken();
		RoleDTO role = new RoleDTO(1, "ADMIN");
		mock.perform(MockMvcRequestBuilders.put("/roles/{id}", 2).content(asJsonString(role))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token)).andExpect(status().isOk());

	}
	public static String asJsonString(final Object obj) throws JsonProcessingException {
		 
			return new ObjectMapper().writeValueAsString(obj);
		
	}


}
