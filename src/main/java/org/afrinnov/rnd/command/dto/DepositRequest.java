package org.afrinnov.rnd.command.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositRequest {
    private String accountId;
    private BigDecimal amount;
}
