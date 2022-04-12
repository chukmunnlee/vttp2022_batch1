package vttp2022.ssf.day6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.StringReader;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

@SpringBootTest
@AutoConfigureMockMvc
public class TestQuoteController {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturn200() {

        // Build the request
        RequestBuilder req = MockMvcRequestBuilders.get("/quote")
            .accept(MediaType.TEXT_HTML_VALUE);

        // Call the controller
        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation", ex);
            return;
        }

        // Get response
        MockHttpServletResponse resp = result.getResponse();
        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception ex) {
            fail("cannot retrieve response payload", ex);
            return;
        }
    }

    @Test
    public void shouldReturn10Quotes() {

        int count = 10;

        // Construct request
        RequestBuilder req = MockMvcRequestBuilders.get("/quote")
                .accept(MediaType.APPLICATION_JSON)
                .queryParam("count", "" + count)
                .header("X-ID", "a header");

        // Make the request
        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation", ex);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        String payload;
        try {
            // JSON
            payload = resp.getContentAsString();
        } catch (Exception ex) {
            fail("cannot retrieve response payload", ex);
            return;
        }

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonArray quotes = reader.readArray();

        assertEquals(count, quotes.size(), 
            "Expect %s quotes. Got %s".formatted(count, quotes.size()));
    }
    
}
