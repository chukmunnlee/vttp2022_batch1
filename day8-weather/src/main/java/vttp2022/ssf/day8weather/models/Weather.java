package vttp2022.ssf.day8weather.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


public class Weather {

    private String city;
    private List<Conditions> conditions = new LinkedList<>();

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public List<Conditions> getConditions() { return conditions; }
    public void setConditions(List<Conditions> conditions) { this.conditions = conditions; }
    public void addCondtion(String description, String icon) {
        Conditions c = new Conditions();
        c.setDescription(description);
        c.setIcon(icon);
        conditions.add(c);
    }

    public static Weather create(String json) throws IOException {
        Weather w = new Weather();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            w.city = o.getString("name");
            w.conditions = o.getJsonArray("weather").stream()
                .map(v -> (JsonObject)v)
                .map(v -> Conditions.create(v))
                .toList();
        }

        return w;
    }

}
