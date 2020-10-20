package com.currency.app.domain.responses;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ExchangeResponse implements Response {
    private BigDecimal originalAmount;
    private String originalCurrency;
    private BigDecimal resultAmount;
    private String resultCurrency;

}
