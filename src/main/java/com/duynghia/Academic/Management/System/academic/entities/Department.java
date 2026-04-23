package com.duynghia.Academic.Management.System.academic.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "department")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {

    @Id
    @Column(name = "department_id", length = 10)
    @NotBlank(message = "DEPARTMENT_ID_REQUIRED")
    @Size(max = 10, message = "DEPARTMENT_ID_INVALID_LENGTH")
    String departmentId;

    @Column(name = "department_name", columnDefinition = "nvarchar(200)", nullable = false)
    @NotBlank(message = "DEPARTMENT_NAME_REQUIRED")
    @Size(max = 200, message = "DEPARTMENT_NAME_INVALID_LENGTH")
    String departmentName;

    @Column(name = "english_name", length = 200)
    @Size(max = 200, message = "ENGLISH_NAME_INVALID_LENGTH")
    String englishName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    @NotNull(message = "FACULTY_REQUIRED")
    Faculty faculty;

    @Column(name = "head_of_department", length = 10)
    @Size(max = 10, message = "HEAD_OF_DEPARTMENT_INVALID_LENGTH")
    String headOfDepartment;

    @Column(name = "address", columnDefinition = "nvarchar(50)")
    @Size(max = 50, message = "ADDRESS_INVALID_LENGTH")
    String address;

    @Column(name = "phone", length = 15)
    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "PHONE_INVALID_FORMAT")
    String phone;

    @Column(name = "email", length = 50)
    @Email(message = "EMAIL_INVALID_FORMAT")
    @Size(max = 50, message = "EMAIL_INVALID_LENGTH")
    String email;
}