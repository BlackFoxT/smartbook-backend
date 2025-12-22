package com.BlackFoxT.smartbook_backend.exception;

public class AdminDeleteForbiddenException extends RuntimeException {
    public AdminDeleteForbiddenException() {
        super("Cannot delete another admin");
    }
}
