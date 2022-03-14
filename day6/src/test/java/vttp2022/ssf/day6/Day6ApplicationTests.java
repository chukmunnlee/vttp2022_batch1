package vttp2022.ssf.day6;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import vttp2022.ssf.day6.controllers.QuoteRestController;
import vttp2022.ssf.day6.services.QuoteService;

@SpringBootTest
class Day6ApplicationTests {

	@Autowired
	private QuoteService quoteSvc;

	@Autowired
	private QuoteRestController quoteRestCtrl;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(quoteSvc);
	}

	@Test
	public void quoteRestCtrlShouldNotBeNull() {
		Assertions.assertNotNull(quoteRestCtrl);

		//MockHttpServletRequestBuilder req = MockHttpServletRequestBuilder.
		//mockMvc.perform(requestBuilder)
	}

	@Test
	public void getQuotesShouldBeEqual() {

		int count = 4;
		Collection<String> result = quoteSvc.getQuotes(count);

		Assertions.assertEquals(count, result.size(), "getQuotes does not return the expected count");

	}

}
