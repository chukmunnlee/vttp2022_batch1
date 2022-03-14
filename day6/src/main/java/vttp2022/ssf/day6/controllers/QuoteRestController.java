package vttp2022.ssf.day6.controllers;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.ssf.day6.services.QuoteService;

@RestController
@RequestMapping(path="/quote", produces=MediaType.APPLICATION_JSON_VALUE)
public class QuoteRestController {

    @Autowired
    private QuoteService quoteSvc;

    // Query parameter count = number
    // If count is not available, count = 1
    @GetMapping
    public ResponseEntity<String> getQuote(
        @RequestHeader(name="X-ID", required = false) String id,
        @RequestParam(name="count", defaultValue="1") Integer count) {

        Collection<String> quotes = quoteSvc.getQuotes(count);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        //List<JsonObject> result = 
        quotes.stream()
            .filter((String q) -> {
                return q.length() > 20;
            })
            .map((String q) -> {
                return Json.createObjectBuilder()
                    .add("quote", q)
                    .add("timestamp", System.currentTimeMillis())
                    .build();
            })
            .forEach((JsonObject o) -> {
                arrBuilder.add(o);
            });
            //.toList();

        /*
        for (String q: quotes) {
            JsonObject result = Json.createObjectBuilder()
                .add("quote", q)
                .add("timestamp", System.currentTimeMillis())
                .build();
            arrBuilder.add(result);
        }
        */
        JsonArray quoteArray = arrBuilder.build();

        return ResponseEntity
            .ok()
            .header("X-ID", id)
            .header("X-My-Header", "Powered by SpringBoot")
            .body(quoteArray.toString());
    }
    
}
