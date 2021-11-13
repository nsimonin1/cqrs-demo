package org.afrinnov.rnd.command.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawRequest {
    private String accountId;
    private BigDecimal amount;

}
