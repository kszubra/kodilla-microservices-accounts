package com.kodilla.microservices.accounts.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionPermissionResponse {

    private Boolean requestAccepted;

    public static TransactionPermissionResponse requestDenied() {
        return new TransactionPermissionResponse(false);
    }

    public static TransactionPermissionResponse requestAccepted() {
        return new TransactionPermissionResponse(true);
    }
}
