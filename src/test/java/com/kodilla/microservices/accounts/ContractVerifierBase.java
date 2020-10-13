package com.kodilla.microservices.accounts;

import com.kodilla.microservices.accounts.api.enumeration.Currency;
import com.kodilla.microservices.accounts.api.response.CustomerAccountsResponse;
import com.kodilla.microservices.accounts.api.snapshot.AccountSnapshot;
import com.kodilla.microservices.accounts.domain.model.Account;
import com.kodilla.microservices.accounts.domain.repository.AccountRepository;
import com.kodilla.microservices.accounts.service.interfaces.AccountService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@Ignore
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ContractVerifierBase {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AccountService service;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(context);
        when(service.getCustomerAccounts(1L)).thenReturn(getMockedResponse());
    }

    private CustomerAccountsResponse getMockedResponse() {
        List<AccountSnapshot> accounts = new ArrayList<>();
        accounts.add(AccountSnapshot.builder()
                .id(95213L)
                .nrb("08897810189710581776778244")
                .currency("PLN")
                .availableFunds(BigDecimal.ONE)
                .build());
        return new CustomerAccountsResponse(accounts);
    }

}
