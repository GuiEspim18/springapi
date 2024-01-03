package com.api.springapi.dto;

import java.util.List;

public class UsersDTO {
    public long id;
    public String name;
    public String email;
    public List<Long> posts;

    public UsersDTO() {
    }

    public UsersDTO(long id, String name, String email, List<Long> posts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.posts = posts;
    }
}
