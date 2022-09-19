package vttp2022.csf.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2022.csf.backend.models.BookSummary;
import vttp2022.csf.backend.serivces.BookService;

@RestController
@RequestMapping(path="/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksRestController {

    @Autowired
    private BookService bookSvc;

    @GetMapping
    public ResponseEntity<String> getBooks(@RequestParam(defaultValue = "20") Integer limit, 
            @RequestParam(defaultValue = "0") Integer offset) {

        List<BookSummary> summaries = bookSvc.getBooks(limit, offset);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (BookSummary summary: summaries)
            arrBuilder.add(summary.toJson());

        return ResponseEntity.ok(arrBuilder.build().toString());
    }
}
