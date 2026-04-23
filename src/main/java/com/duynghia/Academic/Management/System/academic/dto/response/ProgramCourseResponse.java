package com.duynghia.Academic.Management.System.academic.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProgramCourseResponse {
    String programId;
    String programName;
    String courseId;
    String courseName;
    Integer credits;
    Integer semester;
}