package com.example.demo.repository;

import com.example.demo.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Utilizator, Long> {
    Optional<Utilizator> findUserByUid(String uid);

    Optional<Utilizator> findUserByEmail(String email);
}
