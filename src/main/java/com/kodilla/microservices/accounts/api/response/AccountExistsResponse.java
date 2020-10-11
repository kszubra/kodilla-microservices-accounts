package com.kodilla.microservices.accounts.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountExistsResponse {

    private Long accountId;

    private Long customerId;

    private Boolean exists;

    public static AccountExistsResponse exists(Long accountId, Long customerId) {
        return new AccountExistsResponse(accountId, customerId, true);
    }

    public static AccountExistsResponse notExists(Long accountId) {
        return new AccountExistsResponse(accountId, null, true);
    }
}
