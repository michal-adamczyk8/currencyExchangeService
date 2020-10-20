package com.currency.app.api.register;

import com.currency.app.domain.account.model.Account;
import com.currency.app.domain.user.model.CurrencyEnum;
import com.currency.app.domain.user.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Data
public class RegistrationForm {
    private String firstName;
    private String lastName;
    private String pesel;
    private String password;
    private String accountBalance;
    private String currency;

    public User toUser(PasswordEncoder passwordEncoder) {
        Account account = new Account(new BigDecimal(accountBalance), CurrencyEnum.getCurrencyEnumFromValue(currency));
        User user = new User(firstName, lastName, passwordEncoder.encode(password), pesel, account);
        return user;
    }
}
