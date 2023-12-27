package com.api.springapi.models;

public class Login {

    public Login () {
    }

    public Login (String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String email;
    public String password;

}
