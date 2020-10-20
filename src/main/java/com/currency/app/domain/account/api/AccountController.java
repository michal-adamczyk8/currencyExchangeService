package com.currency.app.domain.account.api;

import com.currency.app.domain.responses.Response;
import com.currency.app.domain.account.model.AccountProjection;
import com.currency.app.domain.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "details")
    public ResponseEntity<Response> getAccountDetails(@RequestParam("pesel") String pesel) {
        AccountProjection account = accountService.getAccountProjectionByPesel(pesel);
        if (Objects.isNull(account)) {
            return new ResponseEntity("Account has not been found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(account, HttpStatus.OK);
    }
}
