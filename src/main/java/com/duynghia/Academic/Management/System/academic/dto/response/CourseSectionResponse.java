package com.duynghia.Academic.Management.System.academic.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseSectionResponse {

    String sectionId;

    String sectionName;

    String courseId;

    String courseName;

    Integer credits;

    String lecturerId;

    String lecturerName;

    String academicYear;

    Integer semester;

    Integer phase;

    Integer maxCapacity;

    Integer currentEnrollment;

}
