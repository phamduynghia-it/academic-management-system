package com.duynghia.Academic.Management.System.identity.entities;

import com.duynghia.Academic.Management.System.academic.entities.StudentClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @Column(name = "student_id", length = 10)
    @NotBlank(message = "STUDENT_ID_REQUIRED")
    @Size(max = 10, message = "STUDENT_ID_INVALID_LENGTH")
    String studentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    User user;

    @Column(name = "program_id", length = 25)
    @Size(max = 25, message = "PROGRAM_ID_INVALID_LENGTH")
    String programId;

    @Column(name = "cohort", length = 10)
    String Cohort;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    StudentClass studentClass;


}