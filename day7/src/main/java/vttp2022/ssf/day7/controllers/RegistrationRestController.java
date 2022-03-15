package vttp2022.ssf.day7.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// POST /register
@RestController
@RequestMapping(path="/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRestController {

    @PostMapping
    public ResponseEntity<String> postRegister(@RequestBody String payload) {

        System.out.println(">>>>> payload: " + payload);

        return ResponseEntity.ok("{}");
    }

    
}
