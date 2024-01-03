package com.api.springapi.models;

import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;


@Entity
@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    public long id;

    public String name;

    public String email;

    private String password;

    @OneToMany(mappedBy="user")
    public List<Posts> posts = new ArrayList<>();

    public Users () { }

    public Users(String name, String email, String password, List<Posts> posts) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.posts = posts;
    }

    public void setPassword(String password) {
        final String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = hash;
    }

    public boolean validate(String attempt) {
        final boolean compare =  BCrypt.checkpw(attempt, password);
        return compare;
    }

    @Override
    public String toString () {
        return "Users { id: " + id +
                " name: \"" + name +
                "\", email: \"" + email +
                "\", password: \"" + password + "\" }";
    }
}
