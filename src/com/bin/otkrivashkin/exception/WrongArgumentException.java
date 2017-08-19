package com.bin.otkrivashkin.exception;

public class WrongArgumentException extends Exception {

    private String message;

    public WrongArgumentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
