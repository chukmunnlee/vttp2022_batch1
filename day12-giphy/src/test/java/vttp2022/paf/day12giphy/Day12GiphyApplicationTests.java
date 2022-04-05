package vttp2022.paf.day12giphy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.paf.day12giphy.services.GiphyService;

@SpringBootTest
class Day12GiphyApplicationTests {

	@Autowired
	private GiphyService giphySvc;

	@Test
	void shouldLoad10Images() {
		List<String> gifs = giphySvc.getGiphs("dog");
		assertEquals(10, gifs.size(), "Default number of gifs");

	}

}
