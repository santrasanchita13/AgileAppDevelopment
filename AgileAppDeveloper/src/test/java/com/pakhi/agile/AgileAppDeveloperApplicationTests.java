package com.pakhi.agile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pakhi.agile.beans.AgileUser;
import com.pakhi.agile.constants.ROLES;
import com.pakhi.agile.jparepository.AgileUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AgileAppDeveloperApplication.class)
@WebAppConfiguration
public class AgileAppDeveloperApplicationTests {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private AgileUser agileUser;
	
	@Autowired
	AgileUserRepository agileUserRepository;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	void setConverters(HttpMessageConverter<?>[] httpMessageConverters) {
		this.mappingJackson2HttpMessageConverter = Arrays.asList(httpMessageConverters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);
	}
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		this.agileUserRepository.deleteAllInBatch();
		this.agileUserRepository.save(new AgileUser("santra.sanchita13@gmail.com", ROLES.DEVELOPER, "Sanchita", "Santra"));
	}
	
	@Test
	public void userAlreadyExists() throws Exception {
		mockMvc.perform(post("/register")
				.content(this.json(new AgileUser("santra.sanchita13@gmail.com", ROLES.CUSTOMER, "Sanchita", "Santra")))
				.contentType(contentType))
		.andExpect(status().isConflict());
	}
	
	@Test
	public void registerUser() throws Exception {
		mockMvc.perform(post("/register")
				.content(this.json(new AgileUser("pakhi@gmail.com", ROLES.CUSTOMER, "Pakhi", "S")))
				.contentType(contentType))
		.andExpect(status().isOk());
	}
	
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage message = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, contentType, message);
		return message.getBodyAsString();
	}

}
