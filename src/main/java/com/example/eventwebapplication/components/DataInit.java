package com.example.eventwebapplication.components;

import com.example.eventwebapplication.exceptions.AuthorityNotFoundException;
import com.example.eventwebapplication.exceptions.InvalidLoginException;
import com.example.eventwebapplication.models.Authority;
import com.example.eventwebapplication.models.Event;
import com.example.eventwebapplication.models.User;
import com.example.eventwebapplication.services.AuthorityService;
import com.example.eventwebapplication.services.EventService;
import com.example.eventwebapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.eventwebapplication.utils.Constants.Security.*;

@Component
public class DataInit {
    @Autowired
    private EventService eventService;

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {

        initAuthorityData();
        initEventData();
        initUserData();
    }


    private void initEventData() {
        System.out.println("Starting initializing Event..");
        User user = null;
        try {
            user = userService.findUserByUserName("admin@fineevents.com");
            Event event = new Event();
            event.setName("New Year Eve");
            event.setDescription("New year event on 2023 at Tallinn old town");
            event.setFromDate(LocalDateTime.of(2022, 10, 15, 10, 30));
            event.setToDate(LocalDateTime.of(2022, 10, 18, 10, 30));
            event.setCreator(user);
            if (eventService.findEventByName(event.getName()).isEmpty()) {
                eventService.createEvent(event);
            } else {
                System.out.println("Cannot initialize event. Event title already exists!");
            }
        } catch (InvalidLoginException e) {}

    }

    private void initAuthorityData() {
        System.out.println("Starting initializing Authority..");
        Authority authorityAdmin = new Authority();
        authorityAdmin.setName(AUTHORITY_ADMIN);
        createAuthority(authorityAdmin);

        Authority authorityUser = new Authority();
        authorityUser.setName(AUTHORITY_USER);
        createAuthority(authorityUser);

        Authority authorityOrganizer = new Authority();
        authorityOrganizer.setName(AUTHORITY_ORGANIZER);
        createAuthority(authorityOrganizer);
    }

    private void initUserData() {
        System.out.println("Starting initializing User..");

        Authority optionalAuthority = null;
        try {
            optionalAuthority = authorityService.findAuthorityByName(AUTHORITY_ADMIN);
            User user = new User();
            user.setUserName("admin@fineevents.com");
            user.setPassword("123456");
            user.setAuthority(optionalAuthority);

            try {
                userService.findUserByUserName(user.getUserName());
                System.out.println("User initializing failed. User already exists!");
            } catch (InvalidLoginException e) {
                userService.createUser(user);
            }
        } catch (Exception e) {}
    }

    private void createAuthority(Authority authority) {
        try {
            Authority resultAuthority = authorityService.findAuthorityByName(authority.getName());
            System.out.println("Cannot pre-initialize authority:" + resultAuthority.getName());
        } catch(AuthorityNotFoundException authorityNotFoundException) {
            authorityService.createAuthority(authority);
        }
    }
}
