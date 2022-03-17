package vttp2022.ssf.calserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import org.xml.sax.InputSource;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
class CalServerApplicationTests {

	Random rnd = new SecureRandom();

	@Autowired
	private CalculatorRestController calcCtrl;

	@Autowired
	MockMvc mvc;

	@Test
	void contextLoads() {
		assertNotNull(calcCtrl);
	}

	@Test
	void shouldReturnCorrectResult() throws Exception {
		int oper1 = rnd.nextInt(-50, 50);
		int oper2 = rnd.nextInt(-50, 50);

		// Construct the Json payload
		// { oper1, oper2, ops }
		JsonObject reqPayload = Json.createObjectBuilder()
			.add("oper1", oper1) 
			.add("oper2", oper2) 
			.add("ops", "plus") 
			.build();

		// Construct the HTTP call 
		// what is it that you need to configure the call
		RequestBuilder req = MockMvcRequestBuilders.post("/calculate")
				.header("User-Agent", "junit")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(reqPayload.toString());

		// Make the invocation
		MvcResult resp = mvc.perform(req).andReturn();
		MockHttpServletResponse httpResp = resp.getResponse();

		// Check the status code
		assertEquals(200, httpResp.getStatus());

		// Check the payload
		Optional<JsonObject> opt = string2Json(httpResp.getContentAsString());
		assertTrue(opt.isPresent());

		JsonObject obj = opt.get();
		for (String s: List.of("result", "timestamp", "userAgent"))
			assertFalse(obj.isNull(s));

		assertEquals(oper1 + oper2, obj.getInt("result"));
	}

	@Test
	void shouldFailWithBadOps() throws Exception {

		int oper1 = rnd.nextInt(-50, 50);
		int oper2 = rnd.nextInt(-50, 50);

		// Construct the Json payload
		// { oper1, oper2, ops }
		JsonObject reqPayload = Json.createObjectBuilder()
			.add("oper1", oper1) 
			.add("oper2", oper2) 
			.add("ops", "abc") 
			.build();

		// Construct the HTTP call 
		// what is it that you need to configure the call
		RequestBuilder req = MockMvcRequestBuilders.post("/calculate")
				.header("User-Agent", "junit")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(reqPayload.toString());


		//MvcResult result = mvc.perform(req).andReturn();
		//MockHttpServletResponse resp = result.getResponse();

		assertThrowsExactly(
			NestedServletException.class, 
				() -> {
					mvc.perform(req);
				});

		//assertTrue(resp.getStatus() >= 400);
	}

	public static Optional<JsonObject> string2Json(String s) {
		try (InputStream is = new ByteArrayInputStream(s.getBytes())) {
			JsonReader reader = Json.createReader(is);
			return Optional.of(reader.readObject());
		} catch (Exception ex) {
			System.err.printf("Error: %s\n", ex.getMessage());
			return Optional.empty();
		}
	}
}
