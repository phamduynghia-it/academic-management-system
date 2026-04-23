package com.duynghia.Academic.Management.System.academic.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseInProgramResponse {
    String courseId;
    String courseName;
    Integer credits;
    Integer semester;
}
