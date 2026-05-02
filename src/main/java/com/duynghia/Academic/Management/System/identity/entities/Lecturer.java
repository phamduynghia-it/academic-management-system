package com.duynghia.Academic.Management.System.identity.entities;

import com.duynghia.Academic.Management.System.academic.entities.Department;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "lecturer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lecturer {

    @Id
    @Column(name = "lecturer_id", length = 10)
    @NotBlank(message = "LECTURER_ID_REQUIRED")
    @Size(max = 10, message = "LECTURER_ID_INVALID_LENGTH")
    String lecturerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    Department department;

    @Column(name = "academic_title", columnDefinition = "nvarchar(20)")
    @Size(max = 20, message = "ACADEMIC_TITLE_INVALID_LENGTH")
    String academicTitle;

    @Column(name = "degree", columnDefinition = "nvarchar(20)")
    @Size(max = 20, message = "DEGREE_INVALID_LENGTH")
    String degree;

    @Column(name = "position", columnDefinition = "nvarchar(20)")
    @Size(max = 20, message = "POSITION_INVALID_LENGTH")
    String position;

}