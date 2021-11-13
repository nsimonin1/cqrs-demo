package org.afrinnov.rnd.command.service;

import lombok.RequiredArgsConstructor;
import org.afrinnov.rnd.command.command.CreateAccountCommand;
import org.afrinnov.rnd.command.command.DepositMoneyCommand;
import org.afrinnov.rnd.command.command.WithdrawCommand;
import org.afrinnov.rnd.command.dto.CreateAccountRequest;
import org.afrinnov.rnd.command.dto.DepositRequest;
import org.afrinnov.rnd.command.dto.WithdrawRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AccountCommandService {
    private final CommandGateway commandGateway;

    public CompletableFuture<String> createAccount(CreateAccountRequest createAccountRequest) {
        return commandGateway.send(new CreateAccountCommand(generateAccountId(),
                createAccountRequest.getStartingBalance()));
    }

    public CompletableFuture<String> depositToAccount(DepositRequest depositRequest) {
        return commandGateway.send(new DepositMoneyCommand(depositRequest.getAccountId(), depositRequest.getAmount()));
    }

    public CompletableFuture<String> withdrawFromAccount(WithdrawRequest withdrawRequest) {
        return commandGateway.send(new WithdrawCommand(withdrawRequest.getAccountId(), withdrawRequest.getAmount()));
    }

    private String generateAccountId() {
        String account = RandomStringUtils.randomNumeric(4);
        String agence = RandomStringUtils.randomNumeric(2);
        String order = RandomStringUtils.randomNumeric(3);
        return agence + account + order;
    }
}
