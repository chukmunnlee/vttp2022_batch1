package vttp2022.csf.backend.serivces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.csf.backend.models.Book;
import vttp2022.csf.backend.models.BookSummary;
import vttp2022.csf.backend.respositories.BookRespository;

@Service
public class BookService {

    @Autowired
    private BookRespository bookRepo;

	 public Optional<Book> getBookById(String bookId) {
		 return bookRepo.getBookById(bookId);
	 }

    public List<BookSummary> getBooks(Integer limit, Integer offset) {
        return bookRepo.getBooks(limit, offset);
    }
    
}
