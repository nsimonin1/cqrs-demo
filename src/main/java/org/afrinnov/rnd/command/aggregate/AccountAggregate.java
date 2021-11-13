package org.afrinnov.rnd.command.aggregate;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.afrinnov.rnd.command.command.CreateAccountCommand;
import org.afrinnov.rnd.command.command.DepositMoneyCommand;
import org.afrinnov.rnd.command.command.WithdrawCommand;
import org.afrinnov.rnd.common.event.AccountActivatedEvent;
import org.afrinnov.rnd.common.event.AccountCreditedEvent;
import org.afrinnov.rnd.common.event.AccountCreateEvent;
import org.afrinnov.rnd.common.event.AccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@Slf4j
@NoArgsConstructor
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private BigDecimal balance;
    private String status;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        log.info("Create Account Command received");
        AggregateLifecycle.apply(new AccountCreateEvent(createAccountCommand.getId(), createAccountCommand.getBalance()));
    }

    @EventSourcingHandler
    public void on(AccountCreateEvent event) {
        log.info("An Account Created event occurred");
        this.accountId = event.getId();
        this.balance = event.getBalance();
        this.status = "CREATED";

        AggregateLifecycle.apply(new AccountActivatedEvent(this.accountId, "ACTIVATED"));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
        log.info("An Account Activated event occurred");
        this.status = event.getStatus();
    }

    @CommandHandler
    public void on(DepositMoneyCommand command) {
        log.info("DepositMoneyCommand received");
        AggregateLifecycle.apply(new AccountCreditedEvent(command.getId(), command.getAmount()));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event) {
        log.info("An Account Credited event occurred");
        this.balance = this.balance.add(event.getAmount());
    }

    @CommandHandler
    public void on(WithdrawCommand command) {
        log.info("WithdrawCommand received");
        AggregateLifecycle.apply(new AccountDebitedEvent(command.getId(), command.getAmount()));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event) {
        log.info("An Account Debited event occurred");
        this.balance = this.balance.subtract(event.getAmount());
    }

}
