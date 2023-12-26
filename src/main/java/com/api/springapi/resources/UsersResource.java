package com.api.springapi.resources;

import com.api.springapi.exceptions.NotFoundUserException;
import com.api.springapi.exceptions.UserAlreadyExistsException;
import com.api.springapi.models.Users;
import com.api.springapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value="/users")
public class UsersResource {

    private static final Logger logger = LoggerFactory.getLogger(UsersResource.class);

    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    public List<Users> get() throws Exception {
        try {
            return usersRepository.findAll();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") long id) {
        try {
            final Users users = usersRepository.findById(id).orElseThrow(NotFoundUserException::new);
            return ResponseEntity.ok(users);
        } catch (NotFoundUserException e) {
            logger.error("User not found", e);
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Users data) {
        try {
            final Users users = usersRepository.findByEmail(data.getEmail());
            if (users == null) {
                usersRepository.save(data);
                return ResponseEntity.ok(data);
            }
            throw new UserAlreadyExistsException();
        } catch (UserAlreadyExistsException e) {
            logger.error("User already exists", e);
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Users data){
        try {
            final Users users = usersRepository.findById(data.getId()).orElseThrow(NotFoundUserException::new);
            if (users != null) {
                usersRepository.save(data);
                return ResponseEntity.ok(data);
            }
            throw new NotFoundUserException();
        } catch (NotFoundUserException e) {
            logger.error("User not found", e);
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
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
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

}
