package vttp2022.csf.backend.serivces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.csf.backend.models.BookSummary;
import vttp2022.csf.backend.respositories.BookRespository;

@Service
public class BookService {

    @Autowired
    private BookRespository bookRepo;

    public List<BookSummary> search(Integer limit, Integer offset) {
        return bookRepo.getBooks(limit, offset);
    }
    
}
