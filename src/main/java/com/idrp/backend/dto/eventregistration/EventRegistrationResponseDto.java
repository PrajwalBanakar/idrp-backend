package com.idrp.backend.dto.eventregistration;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRegistrationResponseDto {

    private Long id;
    private String participantName;
    private String email;
    private String phone;
    private String organization;
    private String designation;
    private Integer numberOfAttendees;
    private Long eventId;
    private String eventName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}