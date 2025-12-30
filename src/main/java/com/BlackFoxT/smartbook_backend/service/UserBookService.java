package com.BlackFoxT.smartbook_backend.service;

import com.BlackFoxT.smartbook_backend.dto.library.UserBookResponse;
import com.BlackFoxT.smartbook_backend.exception.*;
import com.BlackFoxT.smartbook_backend.model.Book;
import com.BlackFoxT.smartbook_backend.model.User;
import com.BlackFoxT.smartbook_backend.model.UserBook;
import com.BlackFoxT.smartbook_backend.model.enums.ReadingStatus;
import com.BlackFoxT.smartbook_backend.repository.BookRepository;
import com.BlackFoxT.smartbook_backend.repository.UserBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBookService {

    private final UserBookRepository userBookRepository;
    private final BookRepository bookRepository;

    public UserBookService(
            UserBookRepository userBookRepository,
            BookRepository bookRepository
    ) {
        this.userBookRepository = userBookRepository;
        this.bookRepository = bookRepository;
    }

    public List<UserBookResponse> getUserLibrary(User user) {
        return userBookRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserBookResponse addBookToLibrary(User user, String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        if (userBookRepository.existsByUserAndBook(user, book)) {
            throw new BookAlreadyInLibraryException();
        }

        UserBook userBook = UserBook.builder()
                .user(user)
                .book(book)
                .status(ReadingStatus.WANT_TO_READ)
                .build();

        return toResponse(userBookRepository.save(userBook));
    }

    public UserBookResponse updateStatus(
            User user,
            String isbn,
            ReadingStatus status
    ) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        UserBook userBook = userBookRepository
                .findByUserAndBook(user, book)
                .orElseThrow(BookNotInLibraryException::new);

        userBook.setStatus(status);
        return toResponse(userBookRepository.save(userBook));
    }

    public void removeBookFromLibrary(User user, String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        UserBook userBook = userBookRepository
                .findByUserAndBook(user, book)
                .orElseThrow(BookNotInLibraryException::new);

        userBookRepository.delete(userBook);
    }
    public UserBookResponse rateBook(User user, String isbn, int rating) {
        if (rating < 1 || rating > 5) {
            throw new InvalidRatingException();
        }

        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        UserBook userBook = userBookRepository
                .findByUserAndBook(user, book)
                .orElseThrow(BookNotInLibraryException::new);

        userBook.setRating(rating);
        return toResponse(userBookRepository.save(userBook));
    }

    private UserBookResponse toResponse(UserBook userBook) {
        return new UserBookResponse(
                userBook.getBook().getIsbn(),
                userBook.getBook().getTitle(),
                userBook.getBook().getAuthor(),
                userBook.getStatus(),
                userBook.getRating(),
                userBook.getAddedAt()
        );
    }
}
