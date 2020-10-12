package com.kodilla.microservices.accounts.service.interfaces;

import com.kodilla.microservices.accounts.api.request.AccountCreateRequest;
import com.kodilla.microservices.accounts.api.response.AccountExistsResponse;
import com.kodilla.microservices.accounts.api.response.CustomerAccountsResponse;
import com.kodilla.microservices.accounts.api.snapshot.AccountSnapshot;

public interface AccountService {

    Long createAccount(AccountCreateRequest request);

    AccountSnapshot getAccountSnapshot(Long accountId);

    CustomerAccountsResponse getCustomerAccounts(Long customerId);

    AccountExistsResponse getAccountExists(Long accountId);
}
