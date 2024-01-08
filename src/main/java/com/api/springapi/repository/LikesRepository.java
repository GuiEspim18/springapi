package com.api.springapi.repository;

import com.api.springapi.models.Likes;
import com.api.springapi.models.Posts;
import com.api.springapi.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByUserAndPost (Users user, Posts post);

}
