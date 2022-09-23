package com.example.eventwebapplication.services;

import com.example.eventwebapplication.models.EventComment;

import java.util.List;
import java.util.UUID;

public interface EventCommentService {

    List<EventComment> getAllCommentsBy(UUID event_id);
}
