package org.afrinnov.rnd.common.event;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountCreditedEvent extends BaseEvent<String> {
    private final BigDecimal amount;

    public AccountCreditedEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
