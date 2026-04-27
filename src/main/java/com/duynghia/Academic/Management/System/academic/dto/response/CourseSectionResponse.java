package com.duynghia.Academic.Management.System.academic.dto.response;

import com.duynghia.Academic.Management.System.academic.enums.CourseSectionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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

    LocalDate startDate;

    LocalDate endDate;

    Integer maxCapacity;

    Integer currentEnrollment;

    CourseSectionStatus status;

}
