package com.currency.app.domain.user.service.exception;

public class UserUnder18YearsOldException extends Exception {
    public static final String USER_UNDER_18_YEARS_OLD_MESSAGE = "You must be over 18 years old in order to register!";

    public UserUnder18YearsOldException() {
        super(USER_UNDER_18_YEARS_OLD_MESSAGE);
    }
}
