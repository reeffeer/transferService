package com.example.exception;


public class NotEnoughMoneyException extends IllegalArgumentException {

    public NotEnoughMoneyException() {
        super("Недостаточно средств для совершения операции.");
    }
}
