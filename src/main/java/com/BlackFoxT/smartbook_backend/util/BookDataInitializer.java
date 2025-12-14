package com.BlackFoxT.smartbook_backend.util;

import com.BlackFoxT.smartbook_backend.model.Book;
import com.BlackFoxT.smartbook_backend.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

@Component
public class BookDataInitializer {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BookDataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void loadBooks() throws Exception {

        File file = new ClassPathResource("data/books.json").getFile();

        List<Book> books = objectMapper.readValue(
                file,
                new TypeReference<List<Book>>() {}
        );

        List<Book> newBooks = books.stream()
                .filter(book -> !bookRepository.existsByIsbn(book.getIsbn()))
                .toList();

        if (!newBooks.isEmpty()) {
            bookRepository.saveAll(newBooks);
            System.out.println("Added " + newBooks.size() + " new books.");
        }
    }
}