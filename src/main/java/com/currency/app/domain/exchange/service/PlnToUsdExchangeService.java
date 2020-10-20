package com.currency.app.domain.exchange.service;

import com.currency.app.domain.user.model.CurrencyEnum;
import com.currency.app.nbp.RestTemplateApiClient;
import com.currency.app.nbp.model.Rate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class PlnToUsdExchangeService implements ExchangeService {

    private final RestTemplateApiClient apiClient;

    @Override
    public BigDecimal exchange(String from, String to, BigDecimal amount) {
        Rate rate = apiClient.getCurrentExchangeRate().getBody().getRates().stream().findFirst().orElse(null);
        if (getExchangeCurrencyFrom().toString().equals(from) && getExchangeCurrencyTo().toString().equals(to)) {
            return buy(amount, Double.valueOf(rate.getAsk()));
        }
        if (getExchangeCurrencyTo().toString().equals(from) && getExchangeCurrencyFrom().toString().equals(to)) {
            return sell(amount, Double.valueOf(rate.getAsk()));
        }
        return null;
    }

    @Override
    public CurrencyEnum getExchangeCurrencyFrom() {
        return CurrencyEnum.PLN;
    }

    @Override
    public CurrencyEnum getExchangeCurrencyTo() {
        return CurrencyEnum.USD;
    }

    public BigDecimal buy(BigDecimal amount, Double rate) {
        return amount.divide(new BigDecimal(rate), RoundingMode.HALF_UP);
    }

    public BigDecimal sell(BigDecimal amount, Double rate) {
        return amount.multiply(new BigDecimal(rate)).setScale(2, RoundingMode.HALF_UP);
    }

}
