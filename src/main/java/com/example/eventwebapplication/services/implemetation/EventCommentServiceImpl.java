package com.example.eventwebapplication.services.implemetation;

import com.example.eventwebapplication.models.EventComment;
import com.example.eventwebapplication.repositories.EventCommentRepository;
import com.example.eventwebapplication.services.EventCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EventCommentServiceImpl implements EventCommentService {

    @Autowired
    EventCommentRepository eventCommentRepository;

    @Override
    public List<EventComment> getAllCommentsBy(UUID event_id) {
        return null;
    }
}
