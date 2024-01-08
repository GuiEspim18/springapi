package com.api.springapi.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;

@Entity
@SequenceGenerator(name="likes_sequence", sequenceName="likes_sequence", allocationSize=1)
public class Likes {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="likes_sequence")
    public long id;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="user_id")
    public Users user;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="post_id")
    public Posts post;


    public Likes () {
    }

    public Likes (long id, Users user, Posts post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

}
