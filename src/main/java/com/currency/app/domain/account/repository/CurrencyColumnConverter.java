package com.currency.app.domain.account.repository;

import com.currency.app.domain.user.model.CurrencyEnum;

import javax.persistence.AttributeConverter;

public class CurrencyColumnConverter implements AttributeConverter<CurrencyEnum, String> {

    @Override
    public String convertToDatabaseColumn(CurrencyEnum currencyEnum) {
        return currencyEnum.toString();
    }

    @Override
    public CurrencyEnum convertToEntityAttribute(String currency) {
        return CurrencyEnum.valueOf(currency);
    }
}
