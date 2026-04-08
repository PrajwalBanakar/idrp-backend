package com.idrp.backend.service.impl;

import com.idrp.backend.dto.eventregistration.EventRegistrationRequestDto;
import com.idrp.backend.dto.eventregistration.EventRegistrationResponseDto;
import com.idrp.backend.entity.Event;
import com.idrp.backend.entity.EventRegistration;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.EventRegistrationRepository;
import com.idrp.backend.repository.EventRepository;
import com.idrp.backend.service.EventRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventRegistrationServiceImpl implements EventRegistrationService {

    private final EventRegistrationRepository eventRegistrationRepository;
    private final EventRepository eventRepository;

    @Override
    public EventRegistrationResponseDto createEventRegistration(EventRegistrationRequestDto requestDto) {
        Event event = eventRepository.findById(requestDto.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + requestDto.getEventId()));

        if (eventRegistrationRepository.existsByEmailAndEventId(requestDto.getEmail(), requestDto.getEventId())) {
            throw new DuplicateResourceException(
                    "Participant already registered for this event with email: " + requestDto.getEmail()
            );
        }

        EventRegistration eventRegistration = mapToEntity(requestDto, event);
        EventRegistration savedEventRegistration = eventRegistrationRepository.save(eventRegistration);

        return mapToResponseDto(savedEventRegistration);
    }

    @Override
    public Page<EventRegistrationResponseDto> getAllEventRegistrations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return eventRegistrationRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public EventRegistrationResponseDto getEventRegistrationById(Long id) {
        EventRegistration eventRegistration = eventRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event registration not found with id: " + id));

        return mapToResponseDto(eventRegistration);
    }

    @Override
    public EventRegistrationResponseDto updateEventRegistration(Long id, EventRegistrationRequestDto requestDto) {
        EventRegistration existingEventRegistration = eventRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event registration not found with id: " + id));

        Event event = eventRepository.findById(requestDto.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + requestDto.getEventId()));

        if (eventRegistrationRepository.existsByEmailAndEventIdAndIdNot(
                requestDto.getEmail(),
                requestDto.getEventId(),
                id
        )) {
            throw new DuplicateResourceException(
                    "Another registration already exists for this event with email: " + requestDto.getEmail()
            );
        }

        existingEventRegistration.setParticipantName(requestDto.getParticipantName());
        existingEventRegistration.setEmail(requestDto.getEmail());
        existingEventRegistration.setPhone(requestDto.getPhone());
        existingEventRegistration.setOrganization(requestDto.getOrganization());
        existingEventRegistration.setDesignation(requestDto.getDesignation());
        existingEventRegistration.setNumberOfAttendees(requestDto.getNumberOfAttendees());
        existingEventRegistration.setEvent(event);

        EventRegistration updatedEventRegistration = eventRegistrationRepository.save(existingEventRegistration);
        return mapToResponseDto(updatedEventRegistration);
    }

    @Override
    public void deleteEventRegistration(Long id) {
        EventRegistration eventRegistration = eventRegistrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event registration not found with id: " + id));

        eventRegistrationRepository.delete(eventRegistration);
    }

    private EventRegistration mapToEntity(EventRegistrationRequestDto dto, Event event) {
        return EventRegistration.builder()
                .participantName(dto.getParticipantName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .organization(dto.getOrganization())
                .designation(dto.getDesignation())
                .numberOfAttendees(dto.getNumberOfAttendees())
                .event(event)
                .build();
    }

    private EventRegistrationResponseDto mapToResponseDto(EventRegistration eventRegistration) {
        return EventRegistrationResponseDto.builder()
                .id(eventRegistration.getId())
                .participantName(eventRegistration.getParticipantName())
                .email(eventRegistration.getEmail())
                .phone(eventRegistration.getPhone())
                .organization(eventRegistration.getOrganization())
                .designation(eventRegistration.getDesignation())
                .numberOfAttendees(eventRegistration.getNumberOfAttendees())
                .eventId(eventRegistration.getEvent().getId())
                .eventName(eventRegistration.getEvent().getEventName())
                .createdAt(eventRegistration.getCreatedAt())
                .updatedAt(eventRegistration.getUpdatedAt())
                .build();
    }
}