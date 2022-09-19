package vttp2022.csf.backend.respositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.sql.RowSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.csf.backend.models.Book;
import vttp2022.csf.backend.models.BookSummary;

@Repository
public class BookRespository {

    private static final String SQL_GET_BOOKS = "select book_id, title from book2018 order by title asc limit ? offset ?";

	 private static final String SQL_GET_BOOK_BY_ID = "select * from book2018 where book_id = ?";

    @Autowired
    private JdbcTemplate template;

	 public Optional<Book> getBookById(String bookId) {
		 SqlRowSet rs = template.queryForRowSet(SQL_GET_BOOK_BY_ID, bookId);
		 if (rs.next())
			 return Optional.of(Book.create(rs));
		 return Optional.empty();
	 }

    public List<BookSummary> getBooks(Integer limit, Integer offset) {

        List<BookSummary> summaries = new LinkedList<>();
        

        SqlRowSet rs = template.queryForRowSet(SQL_GET_BOOKS, limit, offset);
        while (rs.next()) {
            BookSummary summary = BookSummary.create(rs);
            summaries.add(summary);
        }
        return summaries;
    }
    
}
