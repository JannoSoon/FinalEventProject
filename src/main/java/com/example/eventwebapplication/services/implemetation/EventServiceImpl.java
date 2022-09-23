package com.example.eventwebapplication.services.implemetation;

import com.example.eventwebapplication.exceptions.EventNotFoundException;
import com.example.eventwebapplication.models.Event;
import com.example.eventwebapplication.models.User;
import com.example.eventwebapplication.services.EventService;

import com.example.eventwebapplication.models.EventComment;
import com.example.eventwebapplication.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;


    @Override
    public void createEvent(Event event) {
        event.setActive(true);
        eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getActiveEvents() {
        return getAllEvents().stream()
                .filter(Event::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> findEventById(UUID id) throws EventNotFoundException {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> findEventByName(String name) {
        return eventRepository.findByName(name);
    }

    @Override
    public void updateEvent(Event event) {
        eventRepository.saveAndFlush(event);
    }

    @Override
    public void deleteEventById(UUID id)  {
        try {
            findEventById(id).ifPresent(event -> {
                event.setActive(false);
                updateEvent(event);
            });
        } catch (EventNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void restoreEventById(UUID id) {
        try {
            findEventById(id).ifPresent(event -> {
                event.setActive(true);
                updateEvent(event);
            });
        } catch (EventNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*@Override
    public ArrayList<EventComment> getEventComments(UUID eventId) {
        return eventRepository.findAllComments(eventId);
    }*/

    @Override
    public void subscribeEvent(String title, UUID userId ) {
           }
}
