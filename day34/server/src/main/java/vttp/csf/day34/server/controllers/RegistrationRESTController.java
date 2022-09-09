package vttp.csf.day34.server.controllers;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.csf.day34.server.models.Registration;
import vttp.csf.day34.server.models.Response;

@RestController
@RequestMapping(path="/api/registration", produces=MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRESTController {

    private Logger logger = Logger.getLogger(RegistrationRESTController.class.getName());

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postRegistration(@RequestBody String payload) {

        Registration reg;
        Response resp;

        logger.info("Payload: %s".formatted(payload));

        // Read the payload and save the data to database
        String id = UUID.randomUUID().toString().substring(0, 8);
        
        try {
            reg = Registration.create(payload);
            reg.setId(id);
        } catch (Exception ex) {
            resp = new Response();
            resp.setCode(400);
            resp.setMessage(ex.getMessage());
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resp.toJson().toString());
        }

        resp = new Response();
        resp.setCode(201);
        resp.setMessage(id);
        resp.setData(reg.toJson().toString());
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(resp.toJson().toString());
    }
}