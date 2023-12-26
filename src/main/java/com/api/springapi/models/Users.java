package com.api.springapi.models;

import jakarta.persistence.*;

import java.text.MessageFormat;

@Entity
@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;

    private String name;

    private String email;

    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString () {
        return "Users { name: \"" + name +
                "\", email: \"" + email +
                "\", password: \"" + password + "\" }";
    }
}
