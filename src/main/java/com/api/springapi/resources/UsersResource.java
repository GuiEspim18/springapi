package com.api.springapi.resources;

import com.api.springapi.exceptions.NotFoundUserException;
import com.api.springapi.exceptions.UserAlreadyExistsException;
import com.api.springapi.models.Users;
import com.api.springapi.repository.UsersRepository;
import com.api.springapi.utils.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/users")
public class UsersResource {

    private static final Logger logger = LoggerFactory.getLogger(UsersResource.class);

    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<?> get() {
        try {
            final List<Users> found = usersRepository.findAll();
            return ResponseEntity.ok(found);
        } catch (Exception e) {
            logger.error("User not found", e);
            return Responses.exception(e);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") long id) {
        try {
            final Users users = usersRepository.findById(id).orElseThrow(NotFoundUserException::new);
            return ResponseEntity.ok(users);
        } catch (NotFoundUserException e) {
            logger.error("User not found", e);
            return Responses.exception(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Users data) {
        try {
            final Users users = usersRepository.findByEmail(data.email);
            if (users == null) {
                usersRepository.save(data);
                return ResponseEntity.ok(data);
            }
            throw new UserAlreadyExistsException();
        } catch (UserAlreadyExistsException e) {
            logger.error("User already exists", e);
            return Responses.exception(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Users data){
        try {
            final Users users = usersRepository.findById(data.id).orElseThrow(NotFoundUserException::new);
            if (users != null) {
                usersRepository.save(data);
                return ResponseEntity.ok(data);
            }
            throw new NotFoundUserException();
        } catch (NotFoundUserException e) {
            logger.error("User not found", e);
            return Responses.exception(e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            final Users users = usersRepository.findById(id).orElseThrow(NotFoundUserException::new);
            if (users != null) {
                usersRepository.deleteById(id);
                Map<String, Boolean> body = new HashMap<>();
                body.put("status", true);
                return ResponseEntity.ok(body);
            }
            throw new NotFoundUserException();
        } catch (NotFoundUserException e) {
            logger.error("User not found", e);
            return Responses.exception(e);
        }
    }

}
