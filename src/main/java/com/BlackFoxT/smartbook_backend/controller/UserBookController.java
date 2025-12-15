package com.BlackFoxT.smartbook_backend.controller;

import com.BlackFoxT.smartbook_backend.model.User;
import com.BlackFoxT.smartbook_backend.model.UserBook;
import com.BlackFoxT.smartbook_backend.model.enums.ReadingStatus;
import com.BlackFoxT.smartbook_backend.service.UserBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class UserBookController {

    private final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    private User mockUser() {
        return User.builder()
                .id(1L)
                .username("demo")
                .email("demo@mail.com")
                .build();
    }

    @GetMapping
    public List<UserBook> getMyLibrary() {
        return userBookService.getUserLibrary(mockUser());
    }

    @PostMapping("/{isbn}")
    public UserBook addBook(@PathVariable String isbn) {
        return userBookService.addBookToLibrary(mockUser(), isbn);
    }

    @PutMapping("/{isbn}/status")
    public UserBook updateStatus(
            @PathVariable String isbn,
            @RequestParam ReadingStatus status
    ) {
        return userBookService.updateStatus(mockUser(), isbn, status);
    }

    @PutMapping("/{isbn}/rating")
    public UserBook rateBook(
            @PathVariable String isbn,
            @RequestParam int rating
    ) {
        return userBookService.rateBook(mockUser(), isbn, rating);
    }
}
