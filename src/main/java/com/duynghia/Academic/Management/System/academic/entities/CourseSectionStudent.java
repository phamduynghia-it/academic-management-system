package com.duynghia.Academic.Management.System.academic.entities;

import com.duynghia.Academic.Management.System.identity.entities.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "course_section_student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseSectionStudent {

    @EmbeddedId
    CourseSectionStudentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sectionId")
    @JoinColumn(name = "section_id")
    CourseSection courseSection;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;


    @Column(name = "process_score")
    Float processScore;

    @Column(name = "final_exam_score")
    Float finalExamScore;

    @Column(name = "final_score")
    Float finalScore;

    @Column(name = "letter_grade", length = 10)
    @Size(max = 10, message = "LETTER_GRADE_INVALID_LENGTH")
    String letterGrade;

    @Column(name = "gpa_4")
    Float gpa4;

    @Column(name = "attempt")
    Integer attempt;
}
