package com.duynghia.Academic.Management.System.academic.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "program_course")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProgramCourse {

    @EmbeddedId
    ProgramCourseId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("programId")
    @JoinColumn(name = "program_id")
    Program program;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    @Column(name = "semester")
    Integer semester;
}