package com.kodilla.microservices.accounts.service;

import com.kodilla.microservices.accounts.api.request.AccountCreateRequest;
import com.kodilla.microservices.accounts.api.snapshot.AccountSnapshot;
import com.kodilla.microservices.accounts.domain.model.Account;
import com.kodilla.microservices.accounts.service.interfaces.AccountNumberGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@AllArgsConstructor
public class AccountMapper {
    private final AccountNumberGenerator accountNumberGenerator;

    public Account mapToEntity(AccountCreateRequest request) {
        return Account.builder()
                .availableFunds(BigDecimal.ZERO)
                .currency(request.getCurrency())
                .customerId(request.getCustomerId())
                .nrb(accountNumberGenerator.generateAccountNumber())
                .build();
    }

    public AccountSnapshot mapToSnapshot(Account account) {
        return AccountSnapshot.builder()
                .id(account.getAccountId())
                .availableFunds(account.getAvailableFunds().setScale(2, RoundingMode.HALF_EVEN))
                .currency(account.getCurrency().toString())
                .nrb(account.getNrb())
                .build();
    }
}
