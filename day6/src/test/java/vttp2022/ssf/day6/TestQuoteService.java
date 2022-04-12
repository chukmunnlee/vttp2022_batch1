package vttp2022.ssf.day6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.ssf.day6.services.QuoteService;

@SpringBootTest
public class TestQuoteService {

    @Autowired
    private QuoteService quoteSvc;

    @Test
    public void shouldReturn3Quote() {

        int count = 3;

        Collection<String> result = quoteSvc.getQuotes(count);

        assertEquals(count, result.size(), "Request the number of quotes");
    }
    
}
