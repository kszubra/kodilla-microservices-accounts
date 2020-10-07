package com.kodilla.microservices.accounts.service;

import com.kodilla.microservices.accounts.service.interfaces.AccountNumberGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SimpleAccountNumberGenerator implements AccountNumberGenerator {

    @Override
    public String generateAccountNumber() {
        return UUID.randomUUID().toString();
    }
}
