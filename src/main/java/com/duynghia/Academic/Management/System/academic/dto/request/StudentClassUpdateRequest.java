package com.duynghia.Academic.Management.System.academic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentClassUpdateRequest {

    @NotBlank(message = "CLASS_NAME_REQUIRED")
    @Size(max = 200, message = "CLASS_NAME_INVALID_LENGTH")
    String className;

    @NotBlank(message = "COURSE_YEAR_REQUIRED")
    @Size(max = 10, message = "COURSE_YEAR_INVALID_LENGTH")
    String courseYear;
}