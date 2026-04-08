package com.idrp.backend.service;

import com.idrp.backend.dto.event.EventRequestDto;
import com.idrp.backend.dto.event.EventResponseDto;
import org.springframework.data.domain.Page;

public interface EventService {

    EventResponseDto createEvent(EventRequestDto requestDto);

    Page<EventResponseDto> getAllEvents(int page, int size);

    EventResponseDto getEventById(Long id);

    EventResponseDto updateEvent(Long id, EventRequestDto requestDto);

    void deleteEvent(Long id);
}