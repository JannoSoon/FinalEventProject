package com.example.eventwebapplication.services;

import com.example.eventwebapplication.exceptions.EventNotFoundException;
import com.example.eventwebapplication.models.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventService {
    /**
     * To create a new event
     *
     * @param event Event
     */
        void createEvent(Event event);

        List<Event>getActiveEvents();


        Optional<Event> findEventById(UUID id) throws EventNotFoundException;


        Optional<Event> findEventByName(String name);


        List<Event> getAllEvents();


        void updateEvent(Event event) throws EventNotFoundException;


        void deleteEventById(UUID id);

        Event restoreEventById(UUID id);



     List<Event> getAllCommentsBy(UUID eventId);

    List<Event> getEventComments(UUID eventId);

    void subscribeEvent(String title, UUID userId);
}

