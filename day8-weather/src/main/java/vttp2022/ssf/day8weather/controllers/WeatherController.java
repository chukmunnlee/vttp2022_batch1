package vttp2022.ssf.day8weather.controllers;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssf.day8weather.models.Weather;
import vttp2022.ssf.day8weather.services.WeatherService;

@Controller
@RequestMapping(path="/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherSvc;

    @GetMapping
    public String getWeather(@RequestParam(required = true) String city
            , Model model) {

        Optional<Weather> opt = weatherSvc.getWeather(city);

        if (opt.isEmpty())
            return "weather";

        model.addAttribute("weather", opt.get());
        return "weather";
    }
}
