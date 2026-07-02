package com.duynghia.Academic.Management.System.academic.entities;

import com.duynghia.Academic.Management.System.identity.entities.Student;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "student_semester_credit", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "academic_year", "semester"})
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentSemesterCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", length = 36)
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

    @Column(name = "academic_year", length = 20, nullable = false)
    String academicYear;

    @Column(name = "semester", nullable = false)
    Integer semester;

    @Column(name = "total_registered_credits", nullable = false)
    Integer totalRegisteredCredits;
}
