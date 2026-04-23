package com.duynghia.Academic.Management.System.academic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseUpdateRequest {
    @NotBlank(message = "COURSE_NAME_REQUIRED")
    @Size(max = 200, message = "COURSE_NAME_INVALID_LENGTH")
    String courseName;

    @Size(max = 200, message = "ENGLISH_NAME_INVALID_LENGTH")
    String englishName;

    @NotNull(message = "CREDITS_REQUIRED")
    Integer credits;

    Float processWeight;

    Float finalWeight;
}
