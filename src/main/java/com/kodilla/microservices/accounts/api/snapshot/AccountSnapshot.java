package com.kodilla.microservices.accounts.api.snapshot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountSnapshot {

    private Long id;

    private String nrb;

    private String currency;

    private BigDecimal availableFunds;
}
