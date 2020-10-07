package com.kodilla.microservices.accounts.web;

import com.kodilla.microservices.accounts.api.request.AccountCreateRequest;
import com.kodilla.microservices.accounts.api.response.CustomerAccountsResponse;
import com.kodilla.microservices.accounts.api.snapshot.AccountSnapshot;
import com.kodilla.microservices.accounts.service.interfaces.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public Long createAccount(@RequestBody @Valid AccountCreateRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping
    public CustomerAccountsResponse getCustomerAccounts(@RequestParam Long customerId) {
        return accountService.getCustomerAccounts(customerId);
    }

    @GetMapping("/{id}")
    public AccountSnapshot getBankAccount(@PathVariable Long id) {
        return accountService.getAccountSnapshot(id);
    }
}
