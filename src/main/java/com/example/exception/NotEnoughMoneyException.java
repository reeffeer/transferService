package com.example.exception;

public class NotEnoughMoneyException extends IllegalArgumentException {

    @Override
    public String getMessage() {
        return "Not enough money. Check balance and try again.";
    }
}
