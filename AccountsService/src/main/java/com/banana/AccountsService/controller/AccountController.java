package com.banana.AccountsService.controller;

import com.banana.AccountsService.model.Account;
import com.banana.AccountsService.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @Valid Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(account));
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable @Min(1) Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccount(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Account>> getAccountsByOwnerId(@PathVariable @Min(1) Long ownerId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.getAccountByOwnerId(ownerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable @Min(1) Long id, @RequestBody @Valid Account account) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.updateAccount(id, account));
    }

    @PutMapping("/{id}/owner/{ownerId}/addBalance")
    public ResponseEntity<Account> addBalance(@PathVariable @Min(1) Long id, @PathVariable @Min(1) Long ownerId, @RequestParam(required = true) @Min(1) int amount ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.addBalance(id, amount, ownerId));
    }

    @PutMapping("/{id}/owner/{ownerId}/withdrawBalance")
    public ResponseEntity<Account> withdrawBalance(@PathVariable @Min(1) Long id, @PathVariable @Min(1) Long ownerId, @RequestParam(required = true) @Min(1) int amount) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.withdrawBalance(id, amount, ownerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccount(@PathVariable @Min(1) Long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/owner/{ownerId}")
    public ResponseEntity deleteAccountsByOwnerId(@PathVariable @Min(1) Long ownerId) {
        accountService.deleteAccountsUsingOwnerId(ownerId);
        return ResponseEntity.noContent().build();
    }
}
