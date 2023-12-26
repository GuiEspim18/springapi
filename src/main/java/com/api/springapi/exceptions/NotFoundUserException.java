package com.api.springapi.exceptions;

public class NotFoundUserException extends Exception {
    public NotFoundUserException() {
        super("Usuário não encontrado!");
    }

    public NotFoundUserException(String message) {
        super(message);
    }
}
