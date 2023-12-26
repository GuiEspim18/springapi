package com.api.springapi.resources;

import com.api.springapi.exceptions.IncorrectPasswordException;
import com.api.springapi.exceptions.NotFoundUserException;
import com.api.springapi.models.Users;
import com.api.springapi.repository.UsersRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    private static final Logger logger = LoggerFactory.getLogger(AuthResource.class);

    @Autowired
    UsersRepository usersRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody Users data) {
        try {
            final Users found = usersRepository.findByEmail(data.getEmail());
            System.out.println(data.getEmail());
            if (found == null) {
                throw new NotFoundUserException();
            }
            if (!BCrypt.checkpw(data.getPassword(), found.getPassword())) {
                throw new IncorrectPasswordException();
            }
            Map<String, String> response = new HashMap<>();
            response.put("name", found.getName());
            response.put("email", found.getEmail());
            return ResponseEntity.ok(response);
        } catch (NotFoundUserException e) {
            logger.error("User not found");
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        } catch (IncorrectPasswordException e) {
            logger.error("Incorrect password");
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

}
