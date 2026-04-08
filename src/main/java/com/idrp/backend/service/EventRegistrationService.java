package com.idrp.backend.service;

import com.idrp.backend.dto.eventregistration.EventRegistrationRequestDto;
import com.idrp.backend.dto.eventregistration.EventRegistrationResponseDto;
import org.springframework.data.domain.Page;

public interface EventRegistrationService {

    EventRegistrationResponseDto createEventRegistration(EventRegistrationRequestDto requestDto);

    Page<EventRegistrationResponseDto> getAllEventRegistrations(int page, int size);

    EventRegistrationResponseDto getEventRegistrationById(Long id);

    EventRegistrationResponseDto updateEventRegistration(Long id, EventRegistrationRequestDto requestDto);

    void deleteEventRegistration(Long id);
}