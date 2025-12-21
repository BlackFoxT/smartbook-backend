package com.BlackFoxT.smartbook_backend.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId);
    }
}
