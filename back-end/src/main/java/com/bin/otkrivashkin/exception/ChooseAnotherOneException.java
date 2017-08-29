package com.bin.otkrivashkin.exception;

public class ChooseAnotherOneException extends Exception {

    private String message;

    public ChooseAnotherOneException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
