package com.example.eventwebapplication.repositories;

import com.example.eventwebapplication.models.EventSubscribers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventSubscribersRepository extends JpaRepository<EventSubscribers, UUID> {


    Optional<EventSubscribers> getEventSubscribersByEvent(UUID id);
}
