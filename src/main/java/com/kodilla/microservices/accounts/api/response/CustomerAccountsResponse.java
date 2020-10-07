package com.kodilla.microservices.accounts.api.response;

import com.kodilla.microservices.accounts.api.snapshot.AccountSnapshot;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CustomerAccountsResponse {
    private List<AccountSnapshot> accounts;
}
