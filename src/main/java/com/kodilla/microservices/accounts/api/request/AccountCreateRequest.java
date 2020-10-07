package com.kodilla.microservices.accounts.api.request;

import com.kodilla.microservices.accounts.api.enumeration.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AccountCreateRequest {
    @NotNull
    private Currency currency;

    @Min(1)
    private Long customerId;
}
