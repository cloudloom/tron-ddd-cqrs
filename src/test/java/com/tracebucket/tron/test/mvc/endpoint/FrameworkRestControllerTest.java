package com.tracebucket.tron.test.mvc.endpoint;

import com.tracebucket.tron.test.mvc.config.MVCConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by ffl on 14-04-2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MVCConfig.class})
@WebAppConfiguration
public class FrameworkRestControllerTest {

	private static MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp(){
		mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void testOverrideRequestMapping() throws Exception {
		MvcResult mvcResult = null;
		mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/test")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		System.out.println("Response = " + response);
	}
}
