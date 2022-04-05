package vttp2022.paf.day11.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp2022.paf.day11.models.Comment;
import vttp2022.paf.day11.models.Game;
import vttp2022.paf.day11.services.GameService;

@RestController
@RequestMapping(path="/game")
public class GameRestController {

    @Autowired
    private GameService gameSvc;

    @GetMapping(path="/{gid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGameAndCommentsById(@PathVariable Integer gid) {
        Optional<Game> opt = gameSvc.getComments(gid);
        JsonObjectBuilder builder = Json.createObjectBuilder();
        if (opt.isEmpty())
            return ResponseEntity.status(404)
                .body(
                    builder.add("error", "not found: %s".formatted(gid))
                        .build().toString()
                );

        Game game = opt.get();

        builder.add("gid", game.getGameId());
        builder.add("name", game.getName());
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Comment c: game.getComments())
            arrBuilder.add("/comment/%s".formatted(c.getCommentId()));
        builder.add("comments", arrBuilder.build());

        return ResponseEntity.ok(builder.build().toString());
    }
    
}
