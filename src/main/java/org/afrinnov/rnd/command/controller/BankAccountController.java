package org.afrinnov.rnd.command.controller;

import lombok.RequiredArgsConstructor;
import org.afrinnov.rnd.command.dto.CreateAccountRequest;
import org.afrinnov.rnd.command.dto.DepositRequest;
import org.afrinnov.rnd.command.dto.WithdrawRequest;
import org.afrinnov.rnd.command.service.AccountCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bank-account")
@RequiredArgsConstructor
public class BankAccountController {
    private final AccountCommandService service;

    @PostMapping("/create")
    ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        try {
            CompletableFuture<String> response = service.createAccount(createAccountRequest);
            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("An error occured");
        }
    }

    @PutMapping("/deposit")
    ResponseEntity<String> deposit(@RequestBody DepositRequest depositRequest) {
        try {
            service.depositToAccount(depositRequest);
            return new ResponseEntity<>("Amount credited", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("An error occured");
        }
    }

    @PutMapping("/withdraw")
    ResponseEntity<String> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        try {
            service.withdrawFromAccount(withdrawRequest);
            return new ResponseEntity<>("Amount debited", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("An error occured");
        }
    }
}
