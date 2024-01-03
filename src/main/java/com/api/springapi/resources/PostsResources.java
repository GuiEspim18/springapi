package com.api.springapi.resources;


import com.api.springapi.dto.GetPostDTO;
import com.api.springapi.dto.GetUserDTO;
import com.api.springapi.dto.PostsDTO;
import com.api.springapi.exceptions.NotFoundPostException;
import com.api.springapi.exceptions.NotFoundUserException;
import com.api.springapi.models.Posts;
import com.api.springapi.models.Users;
import com.api.springapi.repository.PostsRepository;
import com.api.springapi.repository.UsersRepository;
import com.api.springapi.utils.Responses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/posts")
public class PostsResources {

    private static final Logger logger = LoggerFactory.getLogger(PostsResources.class);

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            final List<Posts> found = postsRepository.findAll();
            return ResponseEntity.ok(found);
        } catch (Exception e) {
            logger.error("Post not found ", e);
            return Responses.exception(e);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") long id) {
        try {
            final Posts found = postsRepository.findById(id).orElseThrow(NotFoundPostException::new);
            return ResponseEntity.ok(found);
        } catch (NotFoundPostException e) {
            logger.error("Post not found", e);
            return Responses.exception(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostsDTO data) {
        try {
            final Users user = usersRepository.findById(data.user).orElseThrow(NotFoundUserException::new);
            final Posts post = new Posts(data.message, user);
            postsRepository.save(post);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            logger.error("Cannot post", e);
            return Responses.exception(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Posts data) {
        return ResponseEntity.ok("Hello World");
    }

}
