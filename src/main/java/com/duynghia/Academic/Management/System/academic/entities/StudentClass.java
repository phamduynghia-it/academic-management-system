package com.duynghia.Academic.Management.System.academic.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "class_room")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentClass {

    @Id
    @Column(name = "class_id", length = 25)
    @NotBlank(message = "CLASS_ID_REQUIRED")
    @Size(max = 25, message = "CLASS_ID_INVALID_LENGTH")
    String classId;

    @Column(name = "class_name", columnDefinition = "nvarchar(200)", nullable = false)
    @NotBlank(message = "CLASS_NAME_REQUIRED")
    @Size(max = 200, message = "CLASS_NAME_INVALID_LENGTH")
    String className;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    @NotNull(message = "FACULTY_REQUIRED")
    Faculty faculty;

    @Column(name = "course_year", length = 10)
    @NotBlank(message = "COURSE_YEAR_REQUIRED")
    @Size(max = 10, message = "COURSE_YEAR_INVALID_LENGTH")
    String courseYear;
}