package com.example.app.controller;

import com.example.app.dto.BookDTO;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.mapper.BookMapper;
import com.example.app.model.Book;
import com.example.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/list")
    public String getAllBooks(Model model) {
        Flux<Book> books = bookService.getAllBooks();
        model.addAttribute("books", bookMapper.booksToBookDTOs(books.collectList().block())); // Convert Flux to List synchronously
        return "book-list";
    }

    @GetMapping("/view/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        model.addAttribute("book", bookMapper.bookToBookDTO(book));
        return "book-view";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        bookService.saveBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        model.addAttribute("book", bookMapper.bookToBookDTO(book));
        return "book-edit";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") BookDTO bookDTO) {
        if (!bookService.getBookById(id).isPresent()) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
        Book book = bookMapper.bookDTOToBook(bookDTO);
        book.setId(id);
        bookService.saveBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books/list";
    }
}
