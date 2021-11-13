package org.afrinnov.rnd.query.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.afrinnov.rnd.common.event.AccountActivatedEvent;
import org.afrinnov.rnd.common.event.AccountCreateEvent;
import org.afrinnov.rnd.common.event.AccountCreditedEvent;
import org.afrinnov.rnd.common.event.AccountDebitedEvent;
import org.afrinnov.rnd.query.entity.Account;
import org.afrinnov.rnd.query.query.FindAccountByIdQuery;
import org.afrinnov.rnd.query.repository.AccountRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManageAccountService {
    private final AccountRepository accountRepository;

    @EventHandler
    public void on(AccountCreateEvent event) {
        log.info("Handling Account Created event...");
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getBalance());
        account.setStatus("CREATED");

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event) {
        log.info("Handling Account Activated event...");

        accountRepository.findById(event.getId()).ifPresent(account -> {
            account.setStatus(event.getStatus());
            accountRepository.save(account);
        });
    }

    @EventHandler
    public void on(AccountCreditedEvent event) {
        log.info("Handling Account Credited event...");

        accountRepository.findById(event.getId())
                .ifPresent(account -> {
                    account.setBalance(account.getBalance().add(event.getAmount()));
                    accountRepository.save(account);
                });
    }

    @EventHandler
    public void on(AccountDebitedEvent event) {
        log.info("Handling Account Debited event...");

        accountRepository.findById(event.getId())
                .ifPresent(account -> {
                    account.setBalance(account.getBalance().subtract(event.getAmount()));
                    accountRepository.save(account);
                });
    }

    @QueryHandler
    public Account handler(FindAccountByIdQuery query) {
        log.info("Handling Find Account By Id Query...");
        return accountRepository.findById(query.getId()).orElse(null);
    }
}
