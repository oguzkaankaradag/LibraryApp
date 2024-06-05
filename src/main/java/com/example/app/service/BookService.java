package com.example.app.service;


import com.example.app.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    BookDTO saveBook(BookDTO bookDTO);

    void deleteBook(Long id);
}
