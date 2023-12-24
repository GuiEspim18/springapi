package com.api.springapi.resources;

import com.api.springapi.models.Users;
import com.api.springapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/users")
public class UsersResource {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    public List<Users> get() {
        return usersRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Users> getOne(@PathVariable(value = "id") long id) {
        return usersRepository.findById(id);
    }

    @PostMapping
    public Users create(@RequestBody Users data) {
        return usersRepository.save(data);
    }



}
