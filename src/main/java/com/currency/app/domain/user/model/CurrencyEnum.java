package com.currency.app.domain.user.model;

public enum CurrencyEnum {
    USD("USD"),
    PLN("PLN");

    private String value;

    CurrencyEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static CurrencyEnum getCurrencyEnumFromValue(String value) {
        for (CurrencyEnum currencyEnum : CurrencyEnum.values()) {
            if (currencyEnum.value.equals(value)) {
                return currencyEnum;
            }
        }
        return null;
    }

}
