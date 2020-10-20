package com.currency.app.api.exchange;

public class UnknownCurrencyProvidedException extends Exception {
    private static final String UNRECOGNIZED_CURRENCY = "Provided currency was not recognized!";

    public UnknownCurrencyProvidedException() {
        super(UNRECOGNIZED_CURRENCY);
    }

}
