package com.example.controller;

import com.example.model.Account;
import com.example.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(@RequestParam(required = false) String name) {
        if (name == null) {
            return accountService.getAllAccounts();
        } else {
            return accountService.findAccountsByName(name);
        }
    }
}
