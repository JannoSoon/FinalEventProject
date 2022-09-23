package com.example.eventwebapplication.services;

import com.example.eventwebapplication.exceptions.EventNotFoundException;
import com.example.eventwebapplication.models.Event;
import com.example.eventwebapplication.models.EventComment;

import java.util.ArrayList;
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

        void restoreEventById(UUID id);

     //List<EventComment> getEventComments(UUID eventId);

     void subscribeEvent(String title, UUID userId);
}

