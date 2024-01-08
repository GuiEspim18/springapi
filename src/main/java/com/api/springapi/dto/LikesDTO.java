package com.api.springapi.dto;

import com.api.springapi.models.Users;

public class LikesDTO {

    public long id;
    public long user;
    public long post;

    public LikesDTO () {
    }

    public LikesDTO (long id, long user, long post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

}
