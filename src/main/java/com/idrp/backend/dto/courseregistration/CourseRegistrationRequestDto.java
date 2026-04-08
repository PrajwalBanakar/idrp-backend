package com.idrp.backend.dto.courseregistration;

import com.idrp.backend.entity.CourseRegistrationStatus;
import com.idrp.backend.entity.CourseType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRegistrationRequestDto {

    @NotBlank(message = "Full name is required")
    @Size(max = 150)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    @Size(max = 150)
    private String email;

    @Size(max = 20)
    private String phone;

    @Size(max = 150)
    private String organization;

    @Size(max = 120)
    private String designation;

    @NotNull(message = "Course type is required")
    private CourseType courseType;

    private String message;

    private CourseRegistrationStatus status;
}