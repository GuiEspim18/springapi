package com.api.springapi.exceptions;

public class NotFoundLikesException extends Exception{
    public NotFoundLikesException() {
        super("Registro de like não encontrado!");
    }

    public NotFoundLikesException(String message) {
        super(message);
    }
}
