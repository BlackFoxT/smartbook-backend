package com.BlackFoxT.smartbook_backend.controller;

import com.BlackFoxT.smartbook_backend.dto.BookResponse;
import com.BlackFoxT.smartbook_backend.mapper.BookMapper;
import com.BlackFoxT.smartbook_backend.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public Page<BookResponse> getBooks(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title,
            Pageable pageable
    ) {
        return bookService
                .getBooks(genre, author, title, pageable)
                .map(bookMapper::toResponse);
    }

    @GetMapping("/{isbn}")
    public BookResponse getBookByIsbn(@PathVariable String isbn) {
        return bookMapper.toResponse(
                bookService.getBookByIsbn(isbn)
        );
    }
}
