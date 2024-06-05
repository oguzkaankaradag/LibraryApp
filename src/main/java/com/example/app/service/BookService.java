package com.example.app.service;


import com.example.app.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getAllBooks();
    Optional<BookDTO> getBookById(Long id);
    BookDTO saveBook(BookDTO bookDTO);
    void deleteBook(Long id);
}
