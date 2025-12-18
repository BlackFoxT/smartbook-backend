package com.BlackFoxT.smartbook_backend.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String field) {
        super(field + " already exists");
    }
}
