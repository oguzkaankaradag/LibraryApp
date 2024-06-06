package com.example.app.mapper;

import com.example.app.dto.BookDTO;
import com.example.app.model.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO bookToBookDTO(Book book);
    Book bookDTOToBook(BookDTO bookDTO);
    List<BookDTO> booksToBookDTOs(List<Book> books);
}
