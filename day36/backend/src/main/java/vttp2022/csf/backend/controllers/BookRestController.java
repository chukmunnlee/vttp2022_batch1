package vttp2022.csf.backend.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.csf.backend.models.Book;
import vttp2022.csf.backend.models.BookResponse;
import vttp2022.csf.backend.serivces.BookService;

@RestController
@RequestMapping(path="/api/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookRestController {

	@Autowired
	private BookService bookSvc;

	@GetMapping(path="{bookId}")
	public ResponseEntity<String> getBook(@PathVariable String bookId) {
		Optional<Book> opt = bookSvc.getBookById(bookId);

		if (opt.isEmpty()) {
			BookResponse resp = new BookResponse();
			resp.setStatus(404);
			resp.setMessage("Book %s not found".formatted(bookId));
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(resp.toJson().toString());
		}

		Book book = opt.get();

		return ResponseEntity.ok(book.toJson().toString());
	}
    
}
