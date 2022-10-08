package com.example.eventwebapplication.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Event extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date fromDate;
    private String fromTime;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;


    private String description;
    private boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE)
    private User creator;
}
