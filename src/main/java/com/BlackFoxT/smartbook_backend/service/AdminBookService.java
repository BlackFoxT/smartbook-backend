package com.BlackFoxT.smartbook_backend.service;

import com.BlackFoxT.smartbook_backend.dto.BookRequest;
import com.BlackFoxT.smartbook_backend.exception.BookNotFoundException;
import com.BlackFoxT.smartbook_backend.model.Book;
import com.BlackFoxT.smartbook_backend.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminBookService {

    private final BookRepository bookRepository;

    public AdminBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(BookRequest request) {
        Book book = Book.builder()
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .author(request.getAuthor())
                .genre(request.getGenre())
                .pages(request.getPages())
                .build();

        return bookRepository.save(book);
    }

    public Book update(String isbn, BookRequest request) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setGenre(request.getGenre());
        book.setPages(request.getPages());

        return bookRepository.save(book);
    }

    public void delete(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        bookRepository.delete(book);
    }
}
