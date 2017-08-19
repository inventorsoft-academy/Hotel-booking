package com.bin.otkrivashkin.exception;

public class NotEnoughMoneyException extends Exception {

    private String message;

    public NotEnoughMoneyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
