package com.currency.app.domain.user.service.exception;

public class InvalidPeselException extends Throwable {
    public static final String INVALID_PESEL_MESSAGE = "Given pesel is invalid!";

    public InvalidPeselException() {
        super(INVALID_PESEL_MESSAGE);
    }
}
