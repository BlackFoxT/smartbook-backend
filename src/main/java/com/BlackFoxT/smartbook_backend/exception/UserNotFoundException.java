package com.BlackFoxT.smartbook_backend.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String value) {
        super("User not found: " + value);
    }
}
