package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.event.EventRequestDto;
import com.idrp.backend.dto.event.EventResponseDto;
import com.idrp.backend.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EventController {

    private final EventService eventService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<EventResponseDto>> createEvent(
            @Valid @RequestBody EventRequestDto requestDto
    ) {
        EventResponseDto createdEvent = eventService.createEvent(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<EventResponseDto>builder()
                        .success(true)
                        .message("Event created successfully")
                        .data(createdEvent)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<EventResponseDto>>> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<EventResponseDto> events = eventService.getAllEvents(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<EventResponseDto>>builder()
                        .success(true)
                        .message("Events fetched successfully")
                        .data(events)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponseDto>> getEventById(@PathVariable Long id) {
        EventResponseDto event = eventService.getEventById(id);

        return ResponseEntity.ok(
                ApiResponse.<EventResponseDto>builder()
                        .success(true)
                        .message("Event fetched successfully")
                        .data(event)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponseDto>> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequestDto requestDto
    ) {
        EventResponseDto updatedEvent = eventService.updateEvent(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<EventResponseDto>builder()
                        .success(true)
                        .message("Event updated successfully")
                        .data(updatedEvent)
                        .build()
        );
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Event deleted successfully")
                        .data(null)
                        .build()
        );
    }
}