package com.example.eventwebapplication.repositories;

import com.example.eventwebapplication.models.Event;
import com.example.eventwebapplication.models.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    Optional<Event> findByName(String name);
    Optional<Event> findById(UUID id);

    Optional<Event> findByFromDate(LocalDateTime fromDate);


}
