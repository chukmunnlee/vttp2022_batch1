package vttp2022.ssf.day6.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonValue;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

@Controller
@RequestMapping(path="/po")
public class PurchaseOrderController {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postPO(@RequestBody MultiValueMap<String, String> form) {
        // data is the name of the <input type="text" name="data">
        final String data = form.getFirst("data");

        System.out.println(">>> POST data: " + data);

        InputStream is = new ByteArrayInputStream(data.getBytes());
        JsonReader reader = Json.createReader(is);
        JsonArray json = reader.readArray();

        try { 
            is.close(); 
        } catch (IOException ex) { }

        System.out.println("Array size: " + json.size());
        for (int i = 0; i < json.size(); i++) {
            System.out.printf("---- idx: %d: value: %s\n", i, json.get(i).toString());
        }

        JsonObject wilma = json.getJsonObject(3);
        Set<String> keys = wilma.keySet();
        for (String k: keys)
            System.out.printf("name: %s, value: %s\n", k, wilma.get(k));

        System.out.println(">>> email: " + wilma.getString("email"));

        return "index";
    }

    @GetMapping
    public String getPO() {
        JsonObject address = Json.createObjectBuilder()
            .add("street", "1 Bedrock Av")
            .add("city", "Bedrock")
            .add("postcode", 123456)
            .build();

        System.out.println(">>>>> address: " + address.toString());

        JsonObject item0 = Json.createObjectBuilder()
            .add("sku", "abc")
            .add("quantity", 3)
            .add("unitPrice", .5)
            .build();

        JsonObject item1 = Json.createObjectBuilder()
            .add("sku", "xyz")
            .add("quantity", 10)
            .add("unitPrice", .5)
            .build();

        JsonArray lineItems = Json.createArrayBuilder()
            .add(item0)
            .add(item1)
            .build();

        System.out.println(">>>>> lineItems: " + lineItems.toString());

        JsonObject po = Json.createObjectBuilder()
            .add("name", "fred")
            .add("address", address)
            .add("lineItems", lineItems)
            .build();

        System.out.println(">>>>> po: " + po.toString());

        return "index";
    }
    
}
