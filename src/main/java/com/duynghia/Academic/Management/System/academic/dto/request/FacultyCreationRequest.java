package com.duynghia.Academic.Management.System.academic.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacultyCreationRequest {
    @NotBlank(message = "FACULTY_ID_REQUIRED")
    @Size(max = 10, message = "FACULTY_ID_INVALID_LENGTH")
    String facultyId;

    @NotBlank(message = "FACULTY_NAME_REQUIRED")
    @Size(max = 200, message = "FACULTY_NAME_INVALID_LENGTH")
    String facultyName;

    @Size(max = 200, message = "ENGLISH_NAME_INVALID_LENGTH")
    String englishName;

    @Size(max = 50, message = "ADDRESS_INVALID_LENGTH")
    String address;

    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "PHONE_INVALID_FORMAT")
    String phone;

    @Email(message = "EMAIL_INVALID_FORMAT")
    @Size(max = 50, message = "EMAIL_INVALID_LENGTH")
    String email;
}
