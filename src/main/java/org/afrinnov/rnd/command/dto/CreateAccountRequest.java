package org.afrinnov.rnd.command.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateAccountRequest {
    private BigDecimal startingBalance;
}
