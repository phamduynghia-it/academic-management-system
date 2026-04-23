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
public class ProgramCourseRequest {
    @NotBlank(message = "COURSE_ID_REQUIRED")
    @Size(max = 25, message = "COURSE_ID_INVALID_LENGTH")
    String courseId;
    @NotNull(message = "SEMETER_ID_REQUIRED")
    Integer semester;
}
