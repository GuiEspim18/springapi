package com.api.springapi.resources;

import com.api.springapi.exceptions.IncorrectPasswordException;
import com.api.springapi.exceptions.NotFoundUserException;
import com.api.springapi.dto.LoginDTO;
import com.api.springapi.models.Users;
import com.api.springapi.repository.UsersRepository;
import com.api.springapi.utils.Responses;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> login(@RequestBody LoginDTO data) {
        try {
            final Users found = usersRepository.findByEmail(data.email);
            if (found == null) {
                throw new NotFoundUserException();
            }
            final boolean validPw = found.validate(data.password);
            if (!validPw) {
                throw new IncorrectPasswordException();
            }
            Map<String, String> response = new HashMap<>();
            response.put("name", found.name);
            response.put("email", found.email);
            return ResponseEntity.ok(response);
        } catch (NotFoundUserException e) {
            logger.error("User not found");
            return Responses.exception(e);
        } catch (IncorrectPasswordException e) {
            logger.error("Incorrect password");
            return Responses.exception(e);
        }
    }


}
