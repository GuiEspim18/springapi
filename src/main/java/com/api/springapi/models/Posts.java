package com.api.springapi.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity
@SequenceGenerator(name="post_sequence", sequenceName="post_sequence", allocationSize=1)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Posts {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="post_sequence")
    public long id;

    public String text;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="user_id")
    public Users user;

    public Posts () {
    }

    public Posts (String text, Users user) {
        this.text = text;
        this.user = user;
    }

}
