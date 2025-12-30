package com.BlackFoxT.smartbook_backend.service;

import com.BlackFoxT.smartbook_backend.dto.user.ProfileResponse;
import com.BlackFoxT.smartbook_backend.dto.user.UpdateProfileRequest;
import com.BlackFoxT.smartbook_backend.dto.user.ChangePasswordRequest;
import com.BlackFoxT.smartbook_backend.exception.InvalidPasswordException;
import com.BlackFoxT.smartbook_backend.model.User;
import com.BlackFoxT.smartbook_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ProfileResponse getProfile(User user) {
        return toProfileResponse(user);
    }

    public ProfileResponse updateProfile(
            User user,
            UpdateProfileRequest request
    ) {
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setAge(request.getAge());

        User saved = userRepository.save(user);
        return toProfileResponse(saved);
    }

    public void changePassword(
            User user,
            ChangePasswordRequest request
    ) {
        if (!passwordEncoder.matches(
                request.getOldPassword(),
                user.getPassword()
        )) {
            throw new InvalidPasswordException();
        }

        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);
    }

    public void deleteProfile(User user) {
        userRepository.delete(user);
    }

    private ProfileResponse toProfileResponse(User user) {
        return new ProfileResponse(
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getName(),
                user.getSurname(),
                user.getAge()
        );
    }
}
