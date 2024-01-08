package com.api.springapi.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@SequenceGenerator(name="likes_sequence", sequenceName="likes_sequence", allocationSize=1)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Likes {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="likes_sequence")
    public long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIdentityReference(alwaysAsId = true)
    public Users user;

    @ManyToOne
    @JoinColumn(name="post_id")
    @JsonIgnore
    public Posts post;


    public Likes () {
    }

    public Likes (long id, Users user, Posts post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

}
