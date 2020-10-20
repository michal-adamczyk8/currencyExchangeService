package com.currency.app.domain.account.repository;

import com.currency.app.domain.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getByUserPesel(String pesel);
}
