package com.api.springapi.models;

import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity
@SequenceGenerator(name="post_sequence", sequenceName="post_sequence", allocationSize=1)
public class Posts {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="post_sequence")
    public long id;

    public String message;

    @ManyToOne
    @JoinColumn(name="users")
    public Users user;

    public Posts () {
    }

    public Posts (String message, Users user) {
        this.message = message;
        this.user = user;
    }

}
