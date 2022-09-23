package com.example.eventwebapplication.exceptions;

import java.util.UUID;

public class EventNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public EventNotFoundException(UUID id) {
        super(String.format("Event not found for id: %s", id));
    }

    public EventNotFoundException(String name) {
        super(String.format("Event not found for name: %s", name));
    }
}

