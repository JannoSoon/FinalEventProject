package com.example.eventwebapplication.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class EventSubscribers {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;


    @OneToOne(cascade = CascadeType.MERGE)
    private Event event;


    @OneToMany(cascade = CascadeType.ALL)
    private List<User> subscribers;
}
