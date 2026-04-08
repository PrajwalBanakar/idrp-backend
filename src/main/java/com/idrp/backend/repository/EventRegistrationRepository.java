package com.idrp.backend.repository;

import com.idrp.backend.entity.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

    boolean existsByEmailAndEventId(String email, Long eventId);

    boolean existsByEmailAndEventIdAndIdNot(String email, Long eventId, Long id);
}