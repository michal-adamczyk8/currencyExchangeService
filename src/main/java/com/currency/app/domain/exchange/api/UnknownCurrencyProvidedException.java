package com.currency.app.domain.exchange.api;

public class UnknownCurrencyProvidedException extends Exception {
    private static final String UNRECOGNIZED_CURRENCY = "Provided currency was not recognized!";

    public UnknownCurrencyProvidedException() {
        super(UNRECOGNIZED_CURRENCY);
    }

}
