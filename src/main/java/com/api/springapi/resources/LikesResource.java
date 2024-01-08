package com.api.springapi.resources;

import com.api.springapi.dto.LikesDTO;
import com.api.springapi.exceptions.NotFoundLikesException;
import com.api.springapi.exceptions.NotFoundPostException;
import com.api.springapi.exceptions.NotFoundUserException;
import com.api.springapi.models.Likes;
import com.api.springapi.models.Posts;
import com.api.springapi.models.Users;
import com.api.springapi.repository.LikesRepository;
import com.api.springapi.repository.PostsRepository;
import com.api.springapi.repository.UsersRepository;
import com.api.springapi.utils.Responses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/likes")
public class LikesResource {

    private static final Logger logger = LoggerFactory.getLogger(PostsResource.class);

    @Autowired
    LikesRepository likesRepository;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            final List<Likes> likes = likesRepository.findAll();
            return ResponseEntity.ok(likes);
        } catch (Exception e) {
            logger.error("No likes found", e);
            return Responses.exception(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LikesDTO data) {
        try {
            final Posts post = postsRepository.findById(data.post).orElseThrow(NotFoundPostException::new);
            final Users user = usersRepository.findById(data.user).orElseThrow(NotFoundUserException::new);
            final Optional<Likes> like = likesRepository.findByUserAndPost(user, post);
            if (like.isEmpty()) {
                final Likes likes = new Likes(data.id, user, post);
                likesRepository.save(likes);
                return ResponseEntity.ok(true);
            } else {
                likesRepository.deleteById(like.get().id);
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            logger.error("Error while creating like", e);
            return Responses.exception(e);
        }
    }

}
