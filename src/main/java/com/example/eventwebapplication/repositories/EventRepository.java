package com.example.eventwebapplication.repositories;

import com.example.eventwebapplication.models.Event;
import com.example.eventwebapplication.models.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    Optional<Event> findByName(String name);
    Optional<Event> findById(UUID id);

    Optional<Event> findByFromDate(Date fromDate);
    @Query(value = "select * from Event e where e.name like %:keyword% or e.description like %:keyword%", nativeQuery = true)
    List <Event> findByKeyword(@Param("keyword") String keyword);


}
