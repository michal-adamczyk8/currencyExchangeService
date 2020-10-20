package com.currency.app.domain.exchange.api;

import com.currency.app.domain.exchange.model.Exchange;
import com.currency.app.domain.user.model.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;


@AllArgsConstructor
@Data
public class ExchangeRequest {
    private String amount;
    private String exchangeTo;
    private String exchangeFrom;

    /**
     * Method responsible for converting request to domain object
     * @return
     * @throws UnknownCurrencyProvidedException
     */
    Exchange toExchange() throws UnknownCurrencyProvidedException {
        validateRequest();
        return Exchange.builder().amount(new BigDecimal(amount)).exchangeTo(exchangeTo).exchangeFrom(exchangeFrom).build();
    }

    /**
     * Method responsible for validating currencies provided in request. If not defined in CurrencyEnum custom exception is thrown
     * @throws UnknownCurrencyProvidedException
     */
    private void validateRequest() throws UnknownCurrencyProvidedException {
        if (Objects.isNull(CurrencyEnum.getCurrencyEnumFromValue(exchangeFrom)) || Objects.isNull(CurrencyEnum.getCurrencyEnumFromValue(exchangeTo))) {
            throw new UnknownCurrencyProvidedException();
        }
    }
}
