package com.api.springapi.dto;

public class PostsDTO {
    public long id;
    public String message;
    public long user;

    public PostsDTO() {
    }

    public PostsDTO(long id, String message, long user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }
}
