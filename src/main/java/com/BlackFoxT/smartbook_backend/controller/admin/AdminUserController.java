package com.BlackFoxT.smartbook_backend.controller.admin;

import com.BlackFoxT.smartbook_backend.dto.library.UserBookResponse;
import com.BlackFoxT.smartbook_backend.exception.UserNotFoundException;
import com.BlackFoxT.smartbook_backend.model.User;
import com.BlackFoxT.smartbook_backend.model.enums.Role;
import com.BlackFoxT.smartbook_backend.repository.UserRepository;
import com.BlackFoxT.smartbook_backend.service.UserBookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final UserRepository userRepository;
    private final UserBookService userBookService;

    public AdminUserController(
            UserRepository userRepository,
            UserBookService userBookService
    ) {
        this.userRepository = userRepository;
        this.userBookService = userBookService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{userId}/promote")
    public void promoteToAdmin(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    @PutMapping("/{userId}/demote")
    public void demoteToUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @GetMapping("/{userId}/library")
    public List<UserBookResponse> getUserLibrary(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return userBookService.getUserLibrary(user);
    }
}
