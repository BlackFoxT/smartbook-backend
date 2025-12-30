package com.BlackFoxT.smartbook_backend.controller;

import com.BlackFoxT.smartbook_backend.dto.user.ProfileResponse;
import com.BlackFoxT.smartbook_backend.dto.user.UpdateProfileRequest;
import com.BlackFoxT.smartbook_backend.dto.user.ChangePasswordRequest;
import com.BlackFoxT.smartbook_backend.security.user.CustomUserDetails;
import com.BlackFoxT.smartbook_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ProfileResponse getProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return userService.getProfile(userDetails.getUser());
    }

    @PutMapping
    public ProfileResponse updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateProfileRequest request
    ) {
        return userService.updateProfile(
                userDetails.getUser(),
                request
        );
    }

    @PutMapping("/password")
    public void changePassword(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        userService.changePassword(
                userDetails.getUser(),
                request
        );
    }

    @DeleteMapping
    public void deleteProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        userService.deleteProfile(userDetails.getUser());
    }
}