package com.idrp.backend.repository;

import com.idrp.backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByEventNameAndEventDate(String eventName, LocalDateTime eventDate);

    boolean existsByEventNameAndEventDateAndIdNot(String eventName, LocalDateTime eventDate, Long id);
}