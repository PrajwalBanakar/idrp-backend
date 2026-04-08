package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.eventregistration.EventRegistrationRequestDto;
import com.idrp.backend.dto.eventregistration.EventRegistrationResponseDto;
import com.idrp.backend.service.EventRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-registrations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EventRegistrationController {

    private final EventRegistrationService eventRegistrationService;

    @PostMapping
    public ResponseEntity<ApiResponse<EventRegistrationResponseDto>> createEventRegistration(
            @Valid @RequestBody EventRegistrationRequestDto requestDto
    ) {
        EventRegistrationResponseDto createdEventRegistration =
                eventRegistrationService.createEventRegistration(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<EventRegistrationResponseDto>builder()
                        .success(true)
                        .message("Event registration created successfully")
                        .data(createdEventRegistration)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<EventRegistrationResponseDto>>> getAllEventRegistrations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<EventRegistrationResponseDto> eventRegistrations =
                eventRegistrationService.getAllEventRegistrations(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<EventRegistrationResponseDto>>builder()
                        .success(true)
                        .message("Event registrations fetched successfully")
                        .data(eventRegistrations)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventRegistrationResponseDto>> getEventRegistrationById(
            @PathVariable Long id
    ) {
        EventRegistrationResponseDto eventRegistration =
                eventRegistrationService.getEventRegistrationById(id);

        return ResponseEntity.ok(
                ApiResponse.<EventRegistrationResponseDto>builder()
                        .success(true)
                        .message("Event registration fetched successfully")
                        .data(eventRegistration)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventRegistrationResponseDto>> updateEventRegistration(
            @PathVariable Long id,
            @Valid @RequestBody EventRegistrationRequestDto requestDto
    ) {
        EventRegistrationResponseDto updatedEventRegistration =
                eventRegistrationService.updateEventRegistration(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<EventRegistrationResponseDto>builder()
                        .success(true)
                        .message("Event registration updated successfully")
                        .data(updatedEventRegistration)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEventRegistration(@PathVariable Long id) {
        eventRegistrationService.deleteEventRegistration(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Event registration deleted successfully")
                        .data(null)
                        .build()
        );
    }
}