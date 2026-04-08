package com.idrp.backend.dto.event;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponseDto {

    private Long id;
    private String eventName;
    private String organizerName;
    private String email;
    private String phone;
    private String venue;
    private LocalDateTime eventDate;
    private String category;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}