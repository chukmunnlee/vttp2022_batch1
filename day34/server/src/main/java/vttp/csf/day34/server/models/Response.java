package vttp.csf.day34.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Response {

    private int code = 0;
    private String message = "";
    private String data = "{}";

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("message", message)
            .add("code", code)
            .add("data", data)
            .build();
    }

}
