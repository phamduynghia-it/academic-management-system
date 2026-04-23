package com.duynghia.Academic.Management.System.academic.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProgramWithCoursesResponse {
    String programId;
    String programName;
    String applicableMajor;
    String applicableCohort;
    
    Set<CourseInProgramResponse> courses;
}
