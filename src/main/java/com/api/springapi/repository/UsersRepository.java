package com.api.springapi.repository;

import com.api.springapi.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
