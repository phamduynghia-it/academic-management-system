package com.duynghia.Academic.Management.System.academic.entities;

import com.duynghia.Academic.Management.System.identity.entities.Lecturer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "course_section")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseSection {
    @Id
    @Column(name = "section_id", length = 25)
    @NotBlank(message = "SECTION_ID_REQUIRED")
    @Size(max = 25, message = "SECTION_ID_INVALID_LENGTH")
    String sectionId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    Course course;

    @Column(name = "section_name", columnDefinition = "nvarchar(200)", nullable = false)
    @NotBlank(message = "SECTION_NAME_REQUIRED")
    @Size(max = 200, message = "SECTION_NAME_INVALID_LENGTH")
    String sectionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id")
    Lecturer lecturer;

    @Column(name = "academic_year", length = 20)
    @Size(max = 20, message = "ACADEMIC_YEAR_INVALID_LENGTH")
    String academicYear;

    @Column(name = "semester")
    Integer semester;

    @Column(name = "phase")
    Integer phase;

    @Column(name = "max_capacity")
    @NotNull(message = "MAX_CAPACITY_REQUIRED")
    @Min(value = 1, message = "MAX_CAPACITY_INVALID")
    Integer maxCapacity;


    @Column(name = "current_enrollment")
    @Builder.Default
    Integer currentEnrollment = 0;
    @Version
    Long version;
}