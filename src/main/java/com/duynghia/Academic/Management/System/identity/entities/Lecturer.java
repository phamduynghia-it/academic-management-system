package com.duynghia.Academic.Management.System.identity.entities;

import com.duynghia.Academic.Management.System.academic.entities.Department;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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

    @Column(name = "full_name", columnDefinition = "nvarchar(35)")
    @Size(max = 50, message = "FULL_NAME_INVALID_LENGTH")
    String fullName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    Department department;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "academic_title", columnDefinition = "nvarchar(20)")
    @Size(max = 20, message = "ACADEMIC_TITLE_INVALID_LENGTH")
    String academicTitle;

    @Column(name = "degree", columnDefinition = "nvarchar(20)")
    @Size(max = 20, message = "DEGREE_INVALID_LENGTH")
    String degree;

    @Column(name = "position", columnDefinition = "nvarchar(20)")
    @Size(max = 20, message = "POSITION_INVALID_LENGTH")
    String position;

    @Column(name = "phone", length = 15)
    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "PHONE_INVALID_FORMAT")
    String phone;

}