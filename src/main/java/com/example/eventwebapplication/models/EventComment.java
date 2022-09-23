package com.example.eventwebapplication.models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class EventComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String comment;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Event event;

    @OneToOne(cascade = CascadeType.MERGE)
    private User user;

}
