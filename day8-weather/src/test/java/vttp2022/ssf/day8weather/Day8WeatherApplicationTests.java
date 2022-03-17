package vttp2022.ssf.day8weather;

import  org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import vttp2022.ssf.day8weather.models.Weather;
import vttp2022.ssf.day8weather.services.WeatherService;

@SpringBootTest
@AutoConfigureMockMvc
class Day8WeatherApplicationTests {

	@Autowired
	private WeatherService weatherSvc;

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
		Optional<Weather> opt = weatherSvc.getWeather("singapore");

		Assertions.assertTrue(opt.isPresent());
	}

	@Test
	void shouldReturnWeatherOfTokyo() throws Exception {

		// GET /weather?city=tokyo
		RequestBuilder req = MockMvcRequestBuilders.get("/weather")
				.queryParam("city", "tokyo")
				.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		String payload = result.getResponse().getContentAsString();

		Assertions.assertEquals(200, status);

		Assertions.assertTrue(payload.indexOf("<span>Tokyo</span>") > 0);

		System.out.println("payload: " + payload);

	}

}
