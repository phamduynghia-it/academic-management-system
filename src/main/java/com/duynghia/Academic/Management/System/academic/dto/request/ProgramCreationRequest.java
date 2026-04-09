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
public class ProgramCreationRequest {
    @NotBlank(message = "PROGRAM_ID_REQUIRED")
    @Size(max = 25, message = "PROGRAM_ID_INVALID_LENGTH")
    String programId;
    @NotBlank(message = "PROGRAM_NAME_REQUIRED")
    @Size(max = 200, message = "PROGRAM_NAME_INVALID_LENGTH")
    String programName;
    @Size(max = 200, message = "ENGLISH_NAME_INVALID_LENGTH")
    String englishName;

    @NotBlank(message = "FACULTY_ID_REQUIRED")
    @Size(max = 10, message = "FACULTY_ID_INVALID_LENGTH")
    String facultyId;

    @Size(max = 200, message = "MAJOR_INVALID_LENGTH")
    String applicableMajor;

    @Size(max = 10, message = "COHORT_INVALID_LENGTH")
    String applicableCohort;
}
