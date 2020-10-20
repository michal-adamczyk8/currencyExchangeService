package com.currency.app.domain.exchange.api;

import com.currency.app.domain.responses.ExchangeResponse;
import com.currency.app.domain.exchange.model.Exchange;
import com.currency.app.domain.exchange.service.ExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping(path = "/exchange")
public class ExchangeController {

    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public ResponseEntity exchange(@RequestBody ExchangeRequest request) throws UnknownCurrencyProvidedException {
        Exchange exchange = request.toExchange();
        BigDecimal result = exchangeService.exchange(exchange.getExchangeFrom(), exchange.getExchangeTo(), exchange.getAmount());
        if (Objects.isNull(request)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ExchangeResponse response =
                ExchangeResponse.builder()
                                .originalAmount(exchange.getAmount())
                                .originalCurrency(exchange.getExchangeFrom())
                                .resultAmount(result)
                                .resultCurrency(exchange.getExchangeTo())
                                .build();
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
