package vttp2022.ssf.calserver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

@RestController
@RequestMapping(path="/calculate")
public class CalculatorRestController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postCalculate(@RequestBody String payload,
        @RequestHeader("user-agent") String userAgent) {

        JsonObjectBuilder builder;

        System.out.printf(">>>> user-agent: %s\n", userAgent);
        System.out.printf(">>>> payload: %s\n", payload);

        try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject req = r.readObject();
            builder = Json.createObjectBuilder();

            int oper1 = req.getInt("oper1");
            int oper2 = req.getInt("oper2");
            String ops = req.getString("ops");

            switch (ops) {
                case "plus":
                    builder.add("result", oper1 + oper2);
                    break;
                case "minus":
                    builder.add("result", oper1 - oper2);
                    break;
                case "divide":
                    builder.add("result", oper1 / oper2);
                    break;
                case "multiply":
                    builder.add("result", oper1 * oper2);
                    break;
                default:
                    throw new IllegalArgumentException("Incorrect operator: %s".formatted(ops));
                    /*
                    JsonObject result = Json.createObjectBuilder()
                        .add("error", "Incorrect operator: %s".formatted(ops))
                        .build();
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.toString());
                    */
            }

            builder.add("userAgent", userAgent);
            builder.add("timestamp", (new Date()).toString());

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            throw ex;
            /*
            JsonObject result = Json.createObjectBuilder()
                .add("error", ex.getMessage())
                .build();
            return ResponseEntity.status(400).body(result.toString());
            */
        } catch (Exception ex) {
            JsonObject result = Json.createObjectBuilder()
                .add("error", ex.getMessage())
                .build();
            return ResponseEntity.status(400).body(result.toString());
        } 
        return ResponseEntity.ok(builder.build().toString());
    }
}
