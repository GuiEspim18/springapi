package com.api.springapi.dto;

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
