package com.idrp.backend.service.impl;

import com.idrp.backend.dto.event.EventRequestDto;
import com.idrp.backend.dto.event.EventResponseDto;
import com.idrp.backend.entity.Event;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.EventRepository;
import com.idrp.backend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public EventResponseDto createEvent(EventRequestDto requestDto) {
        if (eventRepository.existsByEventNameAndEventDate(requestDto.getEventName(), requestDto.getEventDate())) {
            throw new DuplicateResourceException(
                    "Event already exists with name '" + requestDto.getEventName() +
                            "' on date " + requestDto.getEventDate());
        }

        Event event = mapToEntity(requestDto);
        Event savedEvent = eventRepository.save(event);
        return mapToResponseDto(savedEvent);
    }

    @Override
    public Page<EventResponseDto> getAllEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return eventRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public EventResponseDto getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        return mapToResponseDto(event);
    }

    @Override
    public EventResponseDto updateEvent(Long id, EventRequestDto requestDto) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        if (eventRepository.existsByEventNameAndEventDateAndIdNot(
                requestDto.getEventName(),
                requestDto.getEventDate(),
                id)) {
            throw new DuplicateResourceException(
                    "Another event already exists with name '" + requestDto.getEventName() +
                            "' on date " + requestDto.getEventDate());
        }
        existingEvent.setEventName(requestDto.getEventName());
        existingEvent.setOrganizerName(requestDto.getOrganizerName());
        existingEvent.setEmail(requestDto.getEmail());
        existingEvent.setPhone(requestDto.getPhone());
        existingEvent.setVenue(requestDto.getVenue());
        existingEvent.setEventDate(requestDto.getEventDate());
        existingEvent.setCategory(requestDto.getCategory());
        existingEvent.setDescription(requestDto.getDescription());

        Event updatedEvent = eventRepository.save(existingEvent);
        return mapToResponseDto(updatedEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        eventRepository.delete(event);
    }

    private Event mapToEntity(EventRequestDto dto) {
        return Event.builder()
                .eventName(dto.getEventName())
                .organizerName(dto.getOrganizerName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .venue(dto.getVenue())
                .eventDate(dto.getEventDate())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .build();
    }

    private EventResponseDto mapToResponseDto(Event event) {
        return EventResponseDto.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .organizerName(event.getOrganizerName())
                .email(event.getEmail())
                .phone(event.getPhone())
                .venue(event.getVenue())
                .eventDate(event.getEventDate())
                .category(event.getCategory())
                .description(event.getDescription())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }
}