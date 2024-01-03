package com.api.springapi.dto;

import com.api.springapi.models.Users;

public class GetPostDTO {

    public long id;
    public String message;
    public Users user;

    public GetPostDTO (long id, String message, Users user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }

}
