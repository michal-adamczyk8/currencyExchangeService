package com.currency.app.domain.account.model;

import com.currency.app.domain.account.repository.CurrencyColumnConverter;
import com.currency.app.domain.user.model.CurrencyEnum;
import com.currency.app.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @OneToOne(mappedBy = "account")
    private User user;

    @Column(name = "balance")
    private final BigDecimal balance;

    @Column(name = "currency")
    @Convert(converter = CurrencyColumnConverter.class)
    private final CurrencyEnum currency;

}
