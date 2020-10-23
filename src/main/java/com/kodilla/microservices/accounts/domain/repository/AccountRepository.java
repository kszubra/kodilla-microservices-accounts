package com.kodilla.microservices.accounts.domain.repository;

import com.kodilla.microservices.accounts.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByNrb(String nrb);

    List<Account> findAllByCustomerId(Long customerId);

    Optional<Account> findByNrb(String nrb);
}
