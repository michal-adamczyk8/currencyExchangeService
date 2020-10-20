package com.currency.app.domain.user.exceptions;

public class PeselNotFoundException extends Exception {
    public PeselNotFoundException(String pesel) {
        super("The user with pesel: " + pesel + " doesn't exist. Please register your account.");
    }
}
