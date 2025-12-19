package com.BlackFoxT.smartbook_backend.exception;

public class BookAlreadyInLibraryException extends RuntimeException {
    public BookAlreadyInLibraryException() {
        super("Book already exists in user's library");
    }
}
