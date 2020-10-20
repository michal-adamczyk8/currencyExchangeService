package com.currency.app.domain.exchange.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Exchange {
    private BigDecimal amount;
    private String exchangeTo;
    private String exchangeFrom;
}
