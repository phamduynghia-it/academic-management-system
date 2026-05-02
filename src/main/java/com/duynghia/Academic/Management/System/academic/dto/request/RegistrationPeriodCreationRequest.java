package com.duynghia.Academic.Management.System.academic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationPeriodCreationRequest {

    @NotBlank(message = "PERIOD_NAME_REQUIRED")
    String periodName;

    @NotBlank(message = "ACADEMIC_YEAR_REQUIRED")
    @Size(max = 20, message = "ACADEMIC_YEAR_INVALID_LENGTH")
    String academicYear;

    @NotNull(message = "SEMESTER_REQUIRED")
    Integer semester;

    @NotNull(message = "PHASE_REQUIRED")
    Integer phase;

    @NotNull(message = "START_TIME_REQUIRED")
    LocalDateTime startTime;

    @NotNull(message = "END_TIME_REQUIRED")
    LocalDateTime endTime;

    String targetCohort;
}
