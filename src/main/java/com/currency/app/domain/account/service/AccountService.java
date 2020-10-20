package com.currency.app.domain.account.service;

import com.currency.app.domain.account.model.Account;
import com.currency.app.domain.account.model.AccountProjection;
import com.currency.app.domain.account.repository.AccountRepository;
import com.currency.app.domain.exchange.service.ExchangeService;
import com.currency.app.domain.user.model.CurrencyEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final ExchangeService exchangeService;
    private final AccountRepository accountRepository;

    public AccountProjection getAccountProjectionByPesel(String pesel) {
        Account account = accountRepository.getByUserPesel(pesel);
        if (Objects.isNull(account)) {
            return null;
        }
        return getAccountProjection(account);
    }

    private AccountProjection getAccountProjection(Account account) {
        BigDecimal accountBalance = account.getBalance();
        CurrencyEnum accountCurrency = account.getCurrency();
        BigDecimal balanceInUsd, balanceInPln;

        balanceInPln = CurrencyEnum.PLN.equals(accountCurrency)
                       ? accountBalance
                       : exchangeService.exchange(CurrencyEnum.USD.toString(), CurrencyEnum.PLN.toString(), accountBalance);
        balanceInUsd = CurrencyEnum.USD.equals(accountCurrency)
                       ? accountBalance
                       : exchangeService.exchange(CurrencyEnum.PLN.toString(), CurrencyEnum.USD.toString(), accountBalance);
        return AccountProjection.builder()
                                .accountId(account.getAccountId())
                                .accountBalanceInPln(balanceInPln)
                                .accountBalanceInUsd(balanceInUsd)
                                .build();
    }
}
