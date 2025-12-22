package com.BlackFoxT.smartbook_backend.controller.admin;

import com.BlackFoxT.smartbook_backend.dto.admin.AdminUserResponse;
import com.BlackFoxT.smartbook_backend.dto.library.UserBookResponse;
import com.BlackFoxT.smartbook_backend.exception.AdminDeleteForbiddenException;
import com.BlackFoxT.smartbook_backend.exception.AdminSelfDeleteException;
import com.BlackFoxT.smartbook_backend.exception.UserNotFoundException;
import com.BlackFoxT.smartbook_backend.model.User;
import com.BlackFoxT.smartbook_backend.model.enums.Role;
import com.BlackFoxT.smartbook_backend.repository.UserRepository;
import com.BlackFoxT.smartbook_backend.security.user.CustomUserDetails;
import com.BlackFoxT.smartbook_backend.service.UserBookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<AdminUserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new AdminUserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole(),
                        user.getUserBooks().size()
                ))
                .toList();
    }

    @PutMapping("/{userId}/promote")
    public void promoteToAdmin(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable Long userId,
            @AuthenticationPrincipal CustomUserDetails adminDetails
    ) {
        User admin = adminDetails.getUser();

        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (admin.getId().equals(targetUser.getId())) {
            throw new AdminSelfDeleteException();
        }

        if (targetUser.getRole() == Role.ADMIN) {
            throw new AdminDeleteForbiddenException();
        }

        userRepository.delete(targetUser);
    }

    @GetMapping("/{userId}/library")
    public List<UserBookResponse> getUserLibrary(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return userBookService.getUserLibrary(user);
    }
}
