package com.api.springapi.resources;


import com.api.springapi.exceptions.NotFoundPostException;
import com.api.springapi.models.Posts;
import com.api.springapi.repository.PostsRepository;
import com.api.springapi.utils.Responses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/posts")
public class PostsResources {

    private static final Logger logger = LoggerFactory.getLogger(PostsResources.class);

    @Autowired
    PostsRepository postsRepository;

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
    public ResponseEntity<?> create(@RequestBody Posts data) {
        try {
            postsRepository.save(data);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            logger.error("Cannot post", e);
            return Responses.exception(e);
        }
    }

}
