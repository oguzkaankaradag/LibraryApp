package com.example.app;

import com.example.app.model.Book;
import com.example.app.repository.BookRepository;
import com.example.app.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class BookServiceIntegrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveBook() {
        // Create a new book
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");

        // Save the book
        bookService.saveBook(book);

        // Check if the book is saved in the database
        Optional<Book> savedBook = bookRepository.findById(book.getId());
        assertTrue(savedBook.isPresent());
        assertEquals(book.getTitle(), savedBook.get().getTitle());
        assertEquals(book.getAuthor(), savedBook.get().getAuthor());
        assertEquals(book.getIsbn(), savedBook.get().getIsbn());
    }

    // Add more test methods for other scenarios (e.g., updateBook, deleteBook) as needed
}
