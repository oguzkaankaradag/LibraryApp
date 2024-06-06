package com.example.app.service;

import com.example.app.model.Book;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

public interface BookService {

//    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book saveBook(Book book);
    void deleteBook(Long id);
    List<Book> searchBooks(String query);
    Flux<Book> getAllBooks();
}
