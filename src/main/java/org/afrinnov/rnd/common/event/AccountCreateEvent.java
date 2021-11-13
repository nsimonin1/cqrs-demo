package org.afrinnov.rnd.common.event;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountCreateEvent extends BaseEvent<String> {
    private final BigDecimal balance;

    public AccountCreateEvent(String id, BigDecimal balance) {
        super(id);
        this.balance = balance;
    }
}
