package com.BlackFoxT.smartbook_backend.repository;

import com.BlackFoxT.smartbook_backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);
}

