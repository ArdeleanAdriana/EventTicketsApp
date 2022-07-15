package com.example.demo.repository;

import com.example.demo.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizerRepo extends JpaRepository<Organizer, Long> {
    Optional<Organizer> findOrganizerByUid(String uid);

    Optional<Organizer> findOrganizerByEmail(String email);
}
