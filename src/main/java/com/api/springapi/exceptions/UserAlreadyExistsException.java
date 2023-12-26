package com.api.springapi.exceptions;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException() {
        super("Este usuário já existe!");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
