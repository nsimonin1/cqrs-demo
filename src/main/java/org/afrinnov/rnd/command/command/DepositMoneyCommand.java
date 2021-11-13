package org.afrinnov.rnd.command.command;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DepositMoneyCommand extends BaseCommand<String> {
    private final BigDecimal amount;

    public DepositMoneyCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
