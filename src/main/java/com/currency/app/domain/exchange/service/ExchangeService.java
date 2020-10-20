package com.currency.app.domain.exchange.service;

import com.currency.app.domain.user.model.CurrencyEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface ExchangeService {
    BigDecimal exchange(String from, String to, BigDecimal amount);

    CurrencyEnum getExchangeCurrencyFrom();

    CurrencyEnum getExchangeCurrencyTo();

}
