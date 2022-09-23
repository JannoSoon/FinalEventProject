package com.example.eventwebapplication.services.implemetation;

import com.example.eventwebapplication.exceptions.EventNotFoundException;
import com.example.eventwebapplication.models.EventSubscribers;
import com.example.eventwebapplication.repositories.EventSubscribersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EventSubscribersServiceImpl implements EventSubscribersService{

    @Autowired
    EventSubscribersRepository eventSubscribersRepository;


    @Override
    public List<EventSubscribers> getEventSubscribers()  {
        return eventSubscribersRepository.findAll();
    }
}
