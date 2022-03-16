package vttp2022.ssf.day8;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
//public class Day8Application implements CommandLineRunner {
public class Day8Application {

	public static void main(String[] args) {
		SpringApplication.run(Day8Application.class, args);
	}

	//@Override
	public void run(String... args) throws Exception {
		String url = "https://httpbin.org/get";

		// Create a template
		url = UriComponentsBuilder.fromUriString(url)
			.queryParam("name", "fred")
			.queryParam("email", "fred@gmail.com")
			.toUriString();

		System.out.println(">>>>> url: " + url);

		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Invocation-Date", (new Date()).toString());
		RequestEntity<Void> req = RequestEntity
			.get(url)
			.headers(headers)
			.accept(MediaType.APPLICATION_JSON)
			.build();

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = template.exchange(req, String.class);

		headers = resp.getHeaders();

		for (Entry<String, List<String>> h : headers.entrySet())
			System.out.printf("header: %s = value: %s\n", h.getKey(), h.getValue());

		System.out.printf(">>>> Status code: %d\n", resp.getStatusCodeValue());
		System.out.printf(">>>> Payload: %s\n", resp.getBody());

		
	}

}
