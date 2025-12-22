package com.BlackFoxT.smartbook_backend.service;

import com.BlackFoxT.smartbook_backend.dto.admin.AdminDashboardResponse;
import com.BlackFoxT.smartbook_backend.model.enums.ReadingStatus;
import com.BlackFoxT.smartbook_backend.repository.BookRepository;
import com.BlackFoxT.smartbook_backend.repository.UserBookRepository;
import com.BlackFoxT.smartbook_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserBookRepository userBookRepository;

    public AdminDashboardService(
            UserRepository userRepository,
            BookRepository bookRepository,
            UserBookRepository userBookRepository
    ) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.userBookRepository = userBookRepository;
    }

    public AdminDashboardResponse getStats() {

        long users = userRepository.count();
        long books = bookRepository.count();
        long totalUserBooks = userBookRepository.count();

        long finished = userBookRepository.countByStatus(ReadingStatus.COMPLETED);
        long reading = userBookRepository.countByStatus(ReadingStatus.READING);
        long wantToRead = userBookRepository.countByStatus(ReadingStatus.WANT_TO_READ);

        long ratedBooks = userBookRepository.countByRatingIsNotNull();

        Double averageRating = userBookRepository.findAverageRating();
        double avgRating = averageRating != null ? averageRating : 0.0;

        return new AdminDashboardResponse(
                users,
                books,
                totalUserBooks,
                wantToRead,
                reading,
                finished,
                avgRating,
                ratedBooks
        );
    }
}
