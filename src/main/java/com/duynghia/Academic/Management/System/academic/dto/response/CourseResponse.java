package com.duynghia.Academic.Management.System.academic.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {
    String courseId;
    String courseName;
    String englishName;
    Integer credits;
    Float processWeight;
    Float finalWeight;

    DepartmentSummaryResponse department;
}