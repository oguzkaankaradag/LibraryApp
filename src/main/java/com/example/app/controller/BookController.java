package com.example.app.controller;

import com.example.app.dto.BookDTO;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.mapper.BookMapper;
import com.example.app.model.Book;
import com.example.app.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return bookMapper.booksToBookDTOs(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        return ResponseEntity.ok(bookMapper.bookToBookDTO(book));
    }

    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam String query) {
        List<Book> books = bookService.searchBooks(query);
        return bookMapper.booksToBookDTOs(books);
    }


    @PostMapping
    public BookDTO createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        book = bookService.saveBook(book);
        return bookMapper.bookToBookDTO(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        if (!bookService.getBookById(id).isPresent()) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
        Book book = bookMapper.bookDTOToBook(bookDTO);
        book.setId(id);
        book = bookService.saveBook(book);
        return ResponseEntity.ok(bookMapper.bookToBookDTO(book));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO> partialUpdateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        if (bookDTO.getTitle() != null) book.setTitle(bookDTO.getTitle());
        if (bookDTO.getAuthor() != null) book.setAuthor(bookDTO.getAuthor());
        if (bookDTO.getIsbn() != null) book.setIsbn(bookDTO.getIsbn());

        book = bookService.saveBook(book);
        return ResponseEntity.ok(bookMapper.bookToBookDTO(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
