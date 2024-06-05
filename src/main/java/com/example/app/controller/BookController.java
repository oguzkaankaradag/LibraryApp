package com.example.app.controller;

import com.example.app.dto.BookDTO;
import com.example.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<BookDTO> bookDTO = bookService.getBookById(id);
        return bookDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.saveBook(bookDTO);
        return ResponseEntity.ok(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Optional<BookDTO> existingBookDTO = bookService.getBookById(id);
        if (existingBookDTO.isPresent()) {
            BookDTO existingBook = existingBookDTO.get();
            existingBook.setTitle(bookDTO.getTitle());
            existingBook.setAuthor(bookDTO.getAuthor());
            existingBook.setIsbn(bookDTO.getIsbn());

            BookDTO updatedBook = bookService.saveBook(existingBook);
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO> patchBook(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<BookDTO> existingBookDTO = bookService.getBookById(id);
        if (existingBookDTO.isPresent()) {
            BookDTO existingBook = existingBookDTO.get();

            // Apply updates from the request body to the existing book DTO
            updates.forEach((key, value) -> {
                switch (key) {
                    case "title":
                        existingBook.setTitle((String) value);
                        break;
                    case "author":
                        existingBook.setAuthor((String) value);
                        break;
                    case "isbn":
                        existingBook.setIsbn((String) value);
                        break;
                    // Add cases for other fields as needed
                }
            });

            // Save the updated book DTO
            BookDTO updatedBook = bookService.saveBook(existingBook);
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
