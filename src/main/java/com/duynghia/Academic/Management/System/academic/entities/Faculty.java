package com.duynghia.Academic.Management.System.academic.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "faculty")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Faculty {

    @Id
    @Column(name = "faculty_id", length = 10)
    @NotBlank(message = "FACULTY_ID_REQUIRED")
    @Size(max = 10, message = "FACULTY_ID_INVALID_LENGTH")
    String facultyId;

    @Column(name = "faculty_name", columnDefinition = "nvarchar(200)", nullable = false)
    @NotBlank(message = "FACULTY_NAME_REQUIRED")
    @Size(max = 200, message = "FACULTY_NAME_INVALID_LENGTH")
    String facultyName;

    @Column(name = "english_name", length = 200)
    @Size(max = 200, message = "ENGLISH_NAME_INVALID_LENGTH")
    String englishName;

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