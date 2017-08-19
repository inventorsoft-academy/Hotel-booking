package com.bin.otkrivashkin.exception;

public class NegativePriceException extends Exception {

    private String message;

    public NegativePriceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
