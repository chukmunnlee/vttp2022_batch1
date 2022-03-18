package vttp2022.ssf.mockassessment2.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2022.ssf.mockassessment2.models.Card;
import vttp2022.ssf.mockassessment2.models.Deck;
import vttp2022.ssf.mockassessment2.services.DoCService;

@Controller
@RequestMapping(path = "/deck")
public class DoCController {

    @Autowired
    private DoCService docSvc;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postDeck(Model model) {

        Deck deck = docSvc.createDeck();

        model.addAttribute("deck", deck);
        model.addAttribute("cards", List.of());
        // <form method="POST" data-th-action="${action}"
        model.addAttribute("action", "/deck/%s".formatted(deck.getDeckId()));

        return "card_game";
    }

    @PostMapping(path = "/{deckId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postDeckId(@PathVariable String deckId , @RequestBody MultiValueMap<String, String> form
            , Model model, HttpSession sess) {

        Integer count = Integer.parseInt(form.getFirst("draw_count"));
        String hiddenDrawn = form.getFirst("hidden_drawn");

        Integer visited = (Integer)sess.getAttribute("visited");
        List<Card> drawn = (List<Card>)sess.getAttribute("drawn");
        if (null == visited) {
            visited = 0;
            drawn = new LinkedList<>();
        }


        Deck deck = docSvc.drawCards(deckId, count);

        visited++;
        for (Card c: deck.getCards())
            drawn.add(c);
        sess.setAttribute("visited", visited);
        sess.setAttribute("drawn", drawn);

        List<String> _hiddenCards = Arrays.asList(hiddenDrawn.split(","));
        List<String> hiddenCards = new LinkedList<>(_hiddenCards);
        for (Card c: deck.getCards())
            hiddenCards.add(c.getImage());

        model.addAttribute("deck", deck);
        //model.addAttribute("cards", drawn);
        model.addAttribute("cards", hiddenCards);
        model.addAttribute("visited", visited);
        model.addAttribute("drawn", String.join(",", hiddenCards));

        if (visited > 3)
            sess.invalidate();

        return "card_game";
    }

}
