package com.api.springapi.dto;

public class PostsDTO {
    public long id;
    public String text;
    public long user;

    public PostsDTO() {
    }

    public PostsDTO(long id, String text, long user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }
}
