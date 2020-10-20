package com.kodilla.microservices.accounts.web;

import com.kodilla.microservices.accounts.api.request.AccountCreateRequest;
import com.kodilla.microservices.accounts.api.request.TransactionPermissionRequest;
import com.kodilla.microservices.accounts.api.response.AccountExistsResponse;
import com.kodilla.microservices.accounts.api.response.CustomerAccountsResponse;
import com.kodilla.microservices.accounts.api.response.TransactionPermissionResponse;
import com.kodilla.microservices.accounts.api.snapshot.AccountSnapshot;
import com.kodilla.microservices.accounts.service.interfaces.AccountService;
import com.kodilla.microservices.commons.AccountsUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    @Value("${application.allow-get-accounts}")
    private boolean allowGetAccounts;

    private final AccountService accountService;

    @PostMapping
    public Long createAccount(@RequestBody @Valid AccountCreateRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping
    public CustomerAccountsResponse getCustomerAccounts(@RequestParam Long customerId) {
        log.info("Get accounts for customer id {}", customerId);
        if(!allowGetAccounts) {
            log.info("Getting accounts disabled");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting accounts is disabled");
        }
        return accountService.getCustomerAccounts(customerId);
    }

    @GetMapping("/request-permission")
    public TransactionPermissionResponse requestTransactionPermission(@Valid TransactionPermissionRequest request) {
        return accountService.requestTransactionPermission(request);
    }

    @GetMapping("/{id}")
    public AccountSnapshot getBankAccount(@PathVariable Long id) {
        return accountService.getAccountSnapshot(id);
    }

    @GetMapping("/exists/{id}")
    public AccountExistsResponse getAccountExists(@PathVariable Long id) {
        return accountService.getAccountExists(id);
    }

    @PutMapping("/account-update")
    public void updateAccounts(@RequestBody AccountsUpdate update) {
        accountService.updateAccounts(update);
    }
}
