package com.BlackFoxT.smartbook_backend.service;

import com.BlackFoxT.smartbook_backend.exception.*;
import com.BlackFoxT.smartbook_backend.model.Book;
import com.BlackFoxT.smartbook_backend.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    /** 📚 Book list + filter + search + sort + pagination */
    public Page<Book> getBooks(
            String genre,
            String author,
            String title,
            Pageable pageable
    ) {

        if (hasText(genre) && hasText(author) && hasText(title)) {
            return bookRepository
                    .findByGenreIgnoreCaseAndAuthorContainingIgnoreCaseAndTitleContainingIgnoreCase(
                            genre, author, title, pageable
                    );
        }

        if (hasText(genre) && hasText(author)) {
            return bookRepository
                    .findByGenreIgnoreCaseAndAuthorContainingIgnoreCase(
                            genre, author, pageable
                    );
        }

        if (hasText(genre) && hasText(title)) {
            return bookRepository
                    .findByGenreIgnoreCaseAndTitleContainingIgnoreCase(
                            genre, title, pageable
                    );
        }

        if (hasText(genre)) {
            return bookRepository.findByGenreIgnoreCase(genre, pageable);
        }

        if (hasText(author)) {
            return bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
        }

        if (hasText(title)) {
            return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        }

        return bookRepository.findAll(pageable);
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }
}
