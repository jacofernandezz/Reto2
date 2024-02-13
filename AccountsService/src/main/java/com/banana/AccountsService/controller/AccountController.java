package com.banana.AccountsService.controller;

import com.banana.AccountsService.model.Account;
import com.banana.AccountsService.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.create(account);
    }

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @GetMapping("/owner/{ownerId}")
    public List<Account> getAccountsByOwnerId(@PathVariable Long ownerId) {
        return accountService.getAccountByOwnerId(ownerId);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @PostMapping("/{id}/add-balance")
    public Account addBalance(@PathVariable Long id, @RequestParam int amount, @RequestParam Long ownerId) {
        return accountService.addBalance(id, amount, ownerId);
    }

    @PostMapping("/{id}/withdraw-balance")
    public Account withdrawBalance(@PathVariable Long id, @RequestParam int amount, @RequestParam Long ownerId) {
        return accountService.withdrawBalance(id, amount, ownerId);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
    }

    @DeleteMapping("/owner/{ownerId}")
    public void deleteAccountsByOwnerId(@PathVariable Long ownerId) {
        accountService.deleteAccountsUsingOwnerId(ownerId);
    }
}
