package vttp2022.ssf.day6.services;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private static final String[] QUOTES = {
        "Walking on water and developing software from a specification are easy if both are frozen.", 
        "I have always wished for my computer to be as easy to use as my telephone; my wish has come true because I can no longer figure out how to use my telephone",
        "If debugging is the process of removing software bugs, then programming must be the process of putting them in.",
        "Vi is a subset of evil",
        "Perl - The only language that looks the same before and after RSA encryption."
    };
    final Random rand = new SecureRandom();

    public String getQuote() {
        final Integer idx = rand.nextInt(QUOTES.length);
        return QUOTES[idx];
    }

    public Collection<String> getQuotes(int count) {
        List<String> q = new LinkedList<>();
        for (int i = 0; i < count; i++)
            q.add(getQuote());
        return q;
    }
    
}