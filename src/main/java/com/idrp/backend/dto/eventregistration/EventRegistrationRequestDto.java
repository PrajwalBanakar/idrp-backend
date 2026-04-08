package com.idrp.backend.dto.eventregistration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRegistrationRequestDto {

    @NotBlank(message = "Participant name is required")
    @Size(max = 120, message = "Participant name must not exceed 120 characters")
    private String participantName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @Size(max = 150, message = "Organization must not exceed 150 characters")
    private String organization;

    @Size(max = 100, message = "Designation must not exceed 100 characters")
    private String designation;

    @Min(value = 1, message = "Number of attendees must be at least 1")
    private Integer numberOfAttendees;

    @NotNull(message = "Event id is required")
    private Long eventId;
}