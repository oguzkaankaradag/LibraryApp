package com.example.app.service.impl;


import com.example.app.dto.BookDTO;
import com.example.app.mapper.BookMapper;
import com.example.app.model.Book;
import com.example.app.repository.BookRepository;
import com.example.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper.INSTANCE::bookToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.map(BookMapper.INSTANCE::bookToBookDTO);
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
        Book savedBook = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookDTO(savedBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
