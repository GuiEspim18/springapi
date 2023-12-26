package com.api.springapi.exceptions;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Senha incorreta!");
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
