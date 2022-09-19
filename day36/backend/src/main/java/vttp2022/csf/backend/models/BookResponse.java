package vttp2022.csf.backend.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class BookResponse {
	private Integer status;
	private String message;

	public void setStatus(Integer status) { this.status = status; }
	public Integer getStatus() { return this.status; }

	public void setMessage(String message) { this.message = message; }
	public String getMessage() { return this.message; }

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("status", status)
			.add("message", message)
			.build();
	}
}
