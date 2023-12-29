package com.api.springapi.exceptions;

public class NotFoundPostException extends Exception{
    public NotFoundPostException() {
        super("Post não encontrado!");
    }

    public NotFoundPostException(String message) {
        super(message);
    }
}
