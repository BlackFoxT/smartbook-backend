package com.BlackFoxT.smartbook_backend.exception;

public class BookNotInLibraryException extends RuntimeException {
    public BookNotInLibraryException() {
        super("Book not found in user's library");
    }
}
