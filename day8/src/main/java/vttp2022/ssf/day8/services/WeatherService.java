package vttp2022.ssf.day8.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    private String apiKey;

    @PostConstruct
    public void init() {
        apiKey = System.getenv("OPEN_WEATHER_MAP");
    }

    public void getWeather(String city) {
        String weatherUrl = UriComponentsBuilder.fromUriString(WEATHER_URL)
            .queryParam("q", city)
            .queryParam("appid", apiKey)
            .toUriString();
        
    }
    
}
