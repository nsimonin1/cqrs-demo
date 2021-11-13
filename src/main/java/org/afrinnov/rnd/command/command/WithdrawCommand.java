package org.afrinnov.rnd.command.command;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class WithdrawCommand extends BaseCommand<String> {
    private final BigDecimal amount;

    public WithdrawCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
