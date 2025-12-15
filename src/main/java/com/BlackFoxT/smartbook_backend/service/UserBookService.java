package com.BlackFoxT.smartbook_backend.service;

import com.BlackFoxT.smartbook_backend.exception.BookNotFoundException;
import com.BlackFoxT.smartbook_backend.model.Book;
import com.BlackFoxT.smartbook_backend.model.User;
import com.BlackFoxT.smartbook_backend.model.UserBook;
import com.BlackFoxT.smartbook_backend.model.enums.ReadingStatus;
import com.BlackFoxT.smartbook_backend.repository.BookRepository;
import com.BlackFoxT.smartbook_backend.repository.UserBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookService {

    private final UserBookRepository userBookRepository;
    private final BookRepository bookRepository;

    public UserBookService(UserBookRepository userBookRepository,
                           BookRepository bookRepository) {
        this.userBookRepository = userBookRepository;
        this.bookRepository = bookRepository;
    }

    public List<UserBook> getUserLibrary(User user) {
        return userBookRepository.findByUser(user);
    }

    public UserBook addBookToLibrary(User user, String isbn) {

        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        if (userBookRepository.existsByUserAndBook(user, book)) {
            throw new IllegalStateException("Book already in user's library");
        }

        UserBook userBook = UserBook.builder()
                .user(user)
                .book(book)
                .status(ReadingStatus.WANT_TO_READ)
                .build();

        return userBookRepository.save(userBook);
    }

    public UserBook updateStatus(User user, String isbn, ReadingStatus status) {

        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        UserBook userBook = userBookRepository
                .findByUserAndBook(user, book)
                .orElseThrow(() -> new IllegalStateException("Book not in library"));

        userBook = UserBook.builder()
                .id(userBook.getId())
                .user(user)
                .book(book)
                .status(status)
                .rating(userBook.getRating())
                .review(userBook.getReview())
                .addedAt(userBook.getAddedAt())
                .build();

        return userBookRepository.save(userBook);
    }

    public UserBook rateBook(User user, String isbn, int rating) {

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        UserBook userBook = userBookRepository
                .findByUserAndBook(user, book)
                .orElseThrow(() -> new IllegalStateException("Book not in library"));

        userBook = UserBook.builder()
                .id(userBook.getId())
                .user(user)
                .book(book)
                .status(userBook.getStatus())
                .rating(rating)
                .review(userBook.getReview())
                .addedAt(userBook.getAddedAt())
                .build();

        return userBookRepository.save(userBook);
    }
}
