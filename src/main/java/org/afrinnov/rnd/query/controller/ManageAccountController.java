package org.afrinnov.rnd.query.controller;

import lombok.RequiredArgsConstructor;
import org.afrinnov.rnd.query.entity.Account;
import org.afrinnov.rnd.query.query.FindAccountByIdQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/manage-account")
@RequiredArgsConstructor
public class ManageAccountController {
    private final QueryGateway queryGateway;

    @GetMapping("/get-account")
    public ResponseEntity<Account> getAccount(@RequestParam String id) {
        Account account = queryGateway.query(new FindAccountByIdQuery(id), Account.class)
                .join();
        return Optional.ofNullable(account)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }
}
