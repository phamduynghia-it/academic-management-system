package com.duynghia.Academic.Management.System.identity.entities;

import com.duynghia.Academic.Management.System.academic.entities.StudentClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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

    @Column(name = "full_name", columnDefinition = "nvarchar(35)")
    @Size(max = 50, message = "FULL_NAME_INVALID_LENGTH")
    String fullName;


    @Column(name = "program_id", length = 25)
    @Size(max = 25, message = "PROGRAM_ID_INVALID_LENGTH")
    String programId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    StudentClass studentClass;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "address", columnDefinition = "nvarchar(250)")
    @Size(max = 250, message = "ADDRESS_INVALID_LENGTH")
    String address;

    @Column(name = "phone", length = 15)
    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "PHONE_INVALID_FORMAT")
    String phone;

}