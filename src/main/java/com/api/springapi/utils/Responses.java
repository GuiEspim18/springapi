package com.api.springapi.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Responses {

    public Responses () {

    }

    public static ResponseEntity<?> exception(Exception e) {
        Map<String, String> body = new HashMap<>();
        body.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

}
