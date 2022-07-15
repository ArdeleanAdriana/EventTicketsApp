package com.example.demo.repository;

import com.example.demo.model.Event;
import com.example.demo.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {
    Optional<Event> findEventByUid(String uid);

    Optional<Event> findEventByName(String name);

    List<Event> findEventByOrganizer(Organizer organizer);
}

