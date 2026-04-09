package com.duynghia.Academic.Management.System.academic.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "course")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {

    @Id
    @Column(name = "course_id", length = 25) // MaHocPhan
    @NotBlank(message = "COURSE_ID_REQUIRED")
    @Size(max = 25, message = "COURSE_ID_INVALID_LENGTH")
    String courseId;

    @Column(name = "course_name", columnDefinition = "nvarchar(200)", nullable = false)
    @NotBlank(message = "COURSE_NAME_REQUIRED")
    @Size(max = 200, message = "COURSE_NAME_INVALID_LENGTH")
    String courseName;

    @Column(name = "english_name", length = 200)
    @Size(max = 200, message = "ENGLISH_NAME_INVALID_LENGTH")
    String englishName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    Department department;

    @Column(name = "credits")
    @NotNull(message = "CREDITS_REQUIRED")
    Integer credits;

    @Column(name = "process_weight")
    Float processWeight;

    @Column(name = "final_weight")
    Float finalWeight;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ProgramCourse> programCourses;
}