package vttp2022.paf.day12giphy.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {

    public static final String GIPHY_SEARCH = "https://api.giphy.com/v1/gifs/search";

    // GIPHY_API_KEY
    @Value("${giphy.api.key}")
    private String giphyKey;

    public List<String> getGiphs(String q) {
        return getGiphs(q, "pg", 10);
    }
    public List<String> getGiphs(String q, String rating) {
        return getGiphs(q, rating, 10);
    }
    public List<String> getGiphs(String q, Integer limit) {
        return getGiphs(q, "pg", limit);
    }
    public List<String> getGiphs(String q, String rating, Integer limit) {
        List<String> result = new LinkedList<>();

        String url = UriComponentsBuilder.fromUriString(GIPHY_SEARCH)
                .queryParam("api_key", giphyKey)
                .queryParam("q", q)
                .queryParam("limit", limit)
                .queryParam("rating", rating)
                .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;
        
        try {
            resp = template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        }

        // image fixed_width.url
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject gifs = reader.readObject();
        JsonArray data = gifs.getJsonArray("data");
        for (int i = 0; i < data.size(); i++) {
            JsonObject gif = data.getJsonObject(i);
            String image = gif.getJsonObject("images").getJsonObject("fixed_width").getString("url");
            result.add(image);
        }
        
        return result;
    }
    
}
