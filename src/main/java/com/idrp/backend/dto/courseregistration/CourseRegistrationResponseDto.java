package com.idrp.backend.dto.courseregistration;

import com.idrp.backend.entity.CourseRegistrationStatus;
import com.idrp.backend.entity.CourseType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRegistrationResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String organization;
    private String designation;
    private CourseType courseType;
    private String message;
    private CourseRegistrationStatus status;
    private LocalDateTime submittedAt;
    private LocalDateTime updatedAt;
}