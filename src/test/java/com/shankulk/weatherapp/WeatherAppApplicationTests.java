package com.shankulk.weatherapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WeatherAppApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void greetingsTest() throws Exception {
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders
				.get("/greetings")
				//.contextPath("/weather")
				.accept(MediaType.TEXT_PLAIN))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	//@Test
	public void testRemoteServiceResponds() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/data")
				//.contextPath("/weather")
				.accept(MediaType.APPLICATION_JSON)
				)
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
