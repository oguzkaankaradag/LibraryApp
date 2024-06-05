package com.example.app.mapper;


import com.example.app.dto.BookDTO;
import com.example.app.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    Book bookDTOToBook(BookDTO bookDTO);

    BookDTO bookToBookDTO(Book book);
}
