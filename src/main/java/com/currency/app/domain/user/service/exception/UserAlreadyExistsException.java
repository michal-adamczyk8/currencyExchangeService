package com.currency.app.domain.user.service.exception;

public class UserAlreadyExistsException extends Exception {
    public static final String PESEL_ALREADY_EXISTS_MESSAGE = "Given pesel already exists in the database!";

    public UserAlreadyExistsException() {
        super(PESEL_ALREADY_EXISTS_MESSAGE);
    }
}
