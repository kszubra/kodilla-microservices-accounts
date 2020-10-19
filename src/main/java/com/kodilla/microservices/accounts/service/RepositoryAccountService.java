package com.kodilla.microservices.accounts.service;

import com.kodilla.microservices.accounts.api.request.AccountCreateRequest;
import com.kodilla.microservices.accounts.api.request.TransactionPermissionRequest;
import com.kodilla.microservices.accounts.api.response.AccountExistsResponse;
import com.kodilla.microservices.accounts.api.response.CustomerAccountsResponse;
import com.kodilla.microservices.accounts.api.response.TransactionPermissionResponse;
import com.kodilla.microservices.accounts.api.snapshot.AccountSnapshot;
import com.kodilla.microservices.accounts.domain.model.Account;
import com.kodilla.microservices.accounts.domain.repository.AccountRepository;
import com.kodilla.microservices.accounts.service.interfaces.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RepositoryAccountService implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    @Override
    @Transactional
    public Long createAccount(AccountCreateRequest request) {
        log.debug("Creating new bank account");

        Long id = accountRepository.save(mapper.mapToEntity(request)).getAccountId();

        log.info("Added bank account with id: {}", id);

        return id;
    }

    @Override
    public TransactionPermissionResponse requestTransactionPermission(TransactionPermissionRequest request) {
        log.info("Requesting transaction permission for amount: {}", request.getValue());
        Account account = accountRepository.findByNrb(request.getFromAccountNumber()).orElseThrow(IllegalArgumentException::new);
        int comparison = account.getAvailableFunds().compareTo(request.getValue());

        return comparison > -1 ? TransactionPermissionResponse.requestAccepted() : TransactionPermissionResponse.requestDenied();
    }

    @Override
    public AccountSnapshot getAccountSnapshot(Long accountId) {
        log.debug("Getting account snapshot with id: {}", accountId);
        return mapper.mapToSnapshot(getById(accountId));
    }

    @Override
    public CustomerAccountsResponse getCustomerAccounts(Long customerId) {
        log.debug("Getting accounts for user: {}", customerId);
        List<AccountSnapshot> accounts = accountRepository.findAllByCustomerId(customerId).stream()
                .map(mapper::mapToSnapshot)
                .collect(Collectors.toList());

        return new CustomerAccountsResponse(accounts);
    }

    @Override
    public AccountExistsResponse getAccountExists(Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if(Objects.nonNull(account)) {
            return AccountExistsResponse.exists(accountId, account.getCustomerId());
        }
        return AccountExistsResponse.notExists(accountId);
    }

    private Account getById(Long id) {
        return accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
