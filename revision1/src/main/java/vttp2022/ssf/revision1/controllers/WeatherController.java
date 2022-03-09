package vttp2022.ssf.revision1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/weather")
public class WeatherController {

    //@GetMapping(path="/")
    @GetMapping
    public String getWeather( @RequestParam(defaultValue="singapore") String city,
        @RequestParam(defaultValue="metric") String units, Model model) {

        model.addAttribute("abc", city);
        model.addAttribute("xyz", units);

        return "weather";
    }
    
}
