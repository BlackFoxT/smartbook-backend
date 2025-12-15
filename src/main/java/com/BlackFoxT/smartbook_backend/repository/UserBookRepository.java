package com.BlackFoxT.smartbook_backend.repository;

import com.BlackFoxT.smartbook_backend.model.Book;
import com.BlackFoxT.smartbook_backend.model.User;
import com.BlackFoxT.smartbook_backend.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    List<UserBook> findByUser(User user);

    Optional<UserBook> findByUserAndBook(User user, Book book);

    boolean existsByUserAndBook(User user, Book book);
}
