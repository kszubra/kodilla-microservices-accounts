package com.kodilla.microservices.accounts.domain.repository;

import com.kodilla.microservices.accounts.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByNrb(String nrb);

    List<Account> findAllByCustomerId(Long customerId);
}
