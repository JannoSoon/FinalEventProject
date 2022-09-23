package com.example.eventwebapplication.repositories;

import com.example.eventwebapplication.models.Event;
import com.example.eventwebapplication.models.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface EventCommentRepository  extends JpaRepository<EventComment, UUID> {


  Optional<EventComment> getEventCommentByEvent(UUID id);
}
