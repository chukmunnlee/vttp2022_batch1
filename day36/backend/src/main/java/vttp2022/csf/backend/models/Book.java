package vttp2022.csf.backend.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Book {
	private String bookId;
	private String title;
	private String authors;
	private String description;
	private Float rating;
	private String imageUrl;

	public void setBookId(String bookId) { this.bookId = bookId; }
	public String getBookId() { return this.bookId; }

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setAuthors(String authors) { this.authors = authors; }
	public String getAuthors() { return this.authors; }

	public void setDescription(String description) { this.description = description; }
	public String getDescription() { return this.description; }

	public void setRating(Float rating) { this.rating = rating; }
	public Float getRating() { return this.rating; }

	public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
	public String getImageUrl() { return this.imageUrl; }

	public static Book create(SqlRowSet rs) {
		Book book = new Book();
		book.setBookId(rs.getString("book_id"));
		book.setTitle(rs.getString("title"));
		book.setAuthors(rs.getString("authors"));
		book.setDescription(rs.getString("description"));
		book.setRating(rs.getFloat("rating"));
		book.setImageUrl(rs.getString("image_url"));
		return book;
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("bookId", bookId)
			.add("title", title)
			.add("authors", authors)
			.add("description", description)
			.add("rating", rating)
			.add("imageUrl", imageUrl)
			.build();
	}
}
