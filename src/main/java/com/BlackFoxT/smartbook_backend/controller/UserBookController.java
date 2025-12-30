package com.BlackFoxT.smartbook_backend.controller;

import com.BlackFoxT.smartbook_backend.dto.library.UserBookResponse;
import com.BlackFoxT.smartbook_backend.model.enums.ReadingStatus;
import com.BlackFoxT.smartbook_backend.security.user.CustomUserDetails;
import com.BlackFoxT.smartbook_backend.service.UserBookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class UserBookController {

    private final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @GetMapping
    public List<UserBookResponse> getMyLibrary(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return userBookService.getUserLibrary(userDetails.getUser());
    }

    @PostMapping("/{isbn}")
    public UserBookResponse addBook(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String isbn
    ) {
        return userBookService.addBookToLibrary(
                userDetails.getUser(),
                isbn
        );
    }

    @PutMapping("/{isbn}/status")
    public UserBookResponse updateStatus(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String isbn,
            @RequestParam ReadingStatus status
    ) {
        return userBookService.updateStatus(
                userDetails.getUser(),
                isbn,
                status
        );
    }

    @PutMapping("/{isbn}/rating")
    public UserBookResponse rateBook(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String isbn,
            @RequestParam int rating
    ) {
        return userBookService.rateBook(
                userDetails.getUser(),
                isbn,
                rating
        );
    }

    @DeleteMapping("/{isbn}")
    public void removeBook(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String isbn
    ) {
        userBookService.removeBookFromLibrary(
                userDetails.getUser(),
                isbn
        );
    }
}
