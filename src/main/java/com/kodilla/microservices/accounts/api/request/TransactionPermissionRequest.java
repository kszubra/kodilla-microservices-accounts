package com.kodilla.microservices.accounts.api.request;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class TransactionPermissionRequest {

    @NotBlank
    private String fromAccountNumber;

    @DecimalMin(value = "0.01", inclusive = true, message = "Minimum value is 0.01")
    private BigDecimal value;
}
