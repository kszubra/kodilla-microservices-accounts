package com.kodilla.microservices.accounts.service;

import com.kodilla.microservices.accounts.api.request.AccountCreateRequest;
import com.kodilla.microservices.accounts.api.response.CustomerAccountsResponse;
import com.kodilla.microservices.accounts.api.snapshot.AccountSnapshot;
import com.kodilla.microservices.accounts.domain.model.Account;
import com.kodilla.microservices.accounts.domain.repository.AccountRepository;
import com.kodilla.microservices.accounts.service.interfaces.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    private Account getById(Long id) {
        return accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
