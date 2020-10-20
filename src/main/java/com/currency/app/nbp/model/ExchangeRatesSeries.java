package com.currency.app.nbp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ExchangeRatesSeries {
    private String currency;
    private String code;
    private List<Rate> rates;
}
