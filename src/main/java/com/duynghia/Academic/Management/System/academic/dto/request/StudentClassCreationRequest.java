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
public class StudentClassCreationRequest {

    @NotBlank(message = "CLASS_ID_REQUIRED")
    @Size(max = 25, message = "CLASS_ID_INVALID_LENGTH")
    String classId;

    @NotBlank(message = "CLASS_NAME_REQUIRED")
    @Size(max = 200, message = "CLASS_NAME_INVALID_LENGTH")
    String className;

    @NotBlank(message = "FACULTY_ID_REQUIRED")
    String facultyId;

    @NotBlank(message = "COURSE_YEAR_REQUIRED")
    @Size(max = 10, message = "COURSE_YEAR_INVALID_LENGTH")
    String courseYear;
}
