package com.idrp.backend.exception;

public class TokenRevokedException extends RuntimeException {

    public TokenRevokedException(String message) {
        super(message);
    }
}