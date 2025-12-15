package com.BlackFoxT.smartbook_backend.repository;

import com.BlackFoxT.smartbook_backend.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);

    Optional<Book> findByIsbn(String isbn);

    Page<Book> findByGenreIgnoreCase(String genre, Pageable pageable);

    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByGenreIgnoreCaseAndAuthorContainingIgnoreCase(
            String genre,
            String author,
            Pageable pageable
    );

    Page<Book> findByGenreIgnoreCaseAndTitleContainingIgnoreCase(
            String genre,
            String title,
            Pageable pageable
    );

    Page<Book> findByGenreIgnoreCaseAndAuthorContainingIgnoreCaseAndTitleContainingIgnoreCase(
            String genre,
            String author,
            String title,
            Pageable pageable
    );
}
