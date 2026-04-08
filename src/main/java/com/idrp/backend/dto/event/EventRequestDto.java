package com.idrp.backend.dto.event;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestDto {

    @NotBlank(message = "Event name is required")
    @Size(max = 150, message = "Event name must not exceed 150 characters")
    private String eventName;

    @NotBlank(message = "Organizer name is required")
    @Size(max = 120, message = "Organizer name must not exceed 120 characters")
    private String organizerName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @Size(max = 150, message = "Venue must not exceed 150 characters")
    private String venue;

    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    private LocalDateTime eventDate;

    @Size(max = 100, message = "Category must not exceed 100 characters")
    private String category;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
}