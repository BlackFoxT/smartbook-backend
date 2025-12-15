package com.BlackFoxT.smartbook_backend.mapper;

import com.BlackFoxT.smartbook_backend.dto.BookResponse;
import com.BlackFoxT.smartbook_backend.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPages()
        );
    }
}