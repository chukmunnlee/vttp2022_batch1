package vttp2022.ssf.day8weather.services;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp2022.ssf.day8weather.models.Weather;

@Service
public class WeatherService {

    private static final String URL = "https://api.openweathermap.org/data/2.5/weather";

    @Value("${open.weather.map}")
    private String apiKey;

    private boolean hasKey;

    @PostConstruct
    private void init() {
        hasKey = null != apiKey;
        System.out.println(">>>> API key set: " + hasKey);
    }

    public Optional<Weather> getWeather(String city) {

        String weatherUrl = UriComponentsBuilder.fromUriString(URL)
            .queryParam("q", city.replaceAll(" ", "+"))
            .queryParam("appid", apiKey)
            .toUriString();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        try {
            resp = template.getForEntity(weatherUrl, String.class);
            Weather w = Weather.create(resp.getBody());
            return Optional.of(w);
        } catch (Exception ex) {
            System.err.printf(">>>> Error: %s\n", ex.getMessage());
            ex.printStackTrace();
        }

        return Optional.empty();
    }
    
}
