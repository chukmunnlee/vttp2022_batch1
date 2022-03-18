package vttp2022.ssf.mockassessment2.services;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp2022.ssf.mockassessment2.models.Deck;

@Service
public class DoCService {

    private static final String BASE_URL = "https://deckofcardsapi.com/api/deck%s";
    private static final String SHUFFLE = "/new/shuffle";
    private static final String DRAW = "/%s/draw";

    public Deck drawCards(String deckId, Integer count) {
        String drawFromDeck = UriComponentsBuilder.fromUriString(BASE_URL.formatted(DRAW.formatted(deckId)))
            .queryParam("count", count)
            .toUriString();

        return invoke(drawFromDeck);
    }

    public Deck createDeck() {
        return createDeck(1);
    }

    public Deck createDeck(int count) {
        String newDeck = UriComponentsBuilder.fromUriString(BASE_URL.formatted(SHUFFLE))
            .queryParam("deck_count", count)
            .toUriString();

        return invoke(newDeck);
    }

    private Deck invoke(String url) {
        RequestEntity<Void> req = RequestEntity.get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        return Deck.create(resp.getBody());
    }
}
