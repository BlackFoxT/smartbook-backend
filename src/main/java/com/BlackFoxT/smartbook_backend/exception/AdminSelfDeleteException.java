package com.BlackFoxT.smartbook_backend.exception;

public class AdminSelfDeleteException extends RuntimeException {
    public AdminSelfDeleteException() {
        super("Admin cannot delete himself");
    }
}

