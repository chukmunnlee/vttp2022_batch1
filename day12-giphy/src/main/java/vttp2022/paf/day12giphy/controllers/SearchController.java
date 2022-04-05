package vttp2022.paf.day12giphy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.paf.day12giphy.services.GiphyService;

@Controller 
@RequestMapping(path="/search")
public class SearchController {

    @Autowired
    private GiphyService giphySvc;

    @GetMapping
    public String getSearch(Model model, @RequestParam String q
            , @RequestParam Integer limit, @RequestParam String rating) {

        System.out.printf(">>> q = %s, limit = %d, rating = %s\n", q, limit, rating);
        List<String> images = giphySvc.getGiphs(q, rating, limit);

        model.addAttribute("q", q);
        model.addAttribute("images", images);

        return "search-result";
    }
}
