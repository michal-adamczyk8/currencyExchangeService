package com.currency.app.domain.account.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountProjection {
    private Long accountId;
    private BigDecimal accountBalanceInUsd;
    private BigDecimal accountBalanceInPln;
}
