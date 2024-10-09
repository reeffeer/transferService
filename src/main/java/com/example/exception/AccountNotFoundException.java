package com.example.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException() {
        super("Аккаунт не найден.");
    }
}