package com.BlackFoxT.smartbook_backend.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("Old password is incorrect");
    }
}