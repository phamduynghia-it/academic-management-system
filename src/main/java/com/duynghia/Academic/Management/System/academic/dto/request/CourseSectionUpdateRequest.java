package com.duynghia.Academic.Management.System.academic.dto.request;

import com.duynghia.Academic.Management.System.academic.enums.CourseSectionStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseSectionUpdateRequest {
    @NotBlank(message = "SECTION_NAME_REQUIRED")
    @Size(max = 200, message = "SECTION_NAME_INVALID_LENGTH")
    String sectionName;

    String lecturerId;

    @NotBlank(message = "ACADEMIC_YEAR_REQUIRED")
    @Size(max = 20, message = "ACADEMIC_YEAR_INVALID_LENGTH")
    String academicYear;

    @NotNull(message = "SEMESTER_REQUIRED")
    Integer semester;

    @NotNull(message = "PHASE_REQUIRED")
    Integer phase;

    @NotNull(message = "PHASE_REQUIRED")
    LocalDate startDate;

    @NotNull(message = "PHASE_REQUIRED")
    LocalDate endDate;

    @NotNull(message = "MAX_CAPACITY_REQUIRED")
    @Min(value = 1, message = "MAX_CAPACITY_INVALID")
    Integer maxCapacity;

    @NotNull(message = "STATUS_REQUIRED")
    CourseSectionStatus status;
}
