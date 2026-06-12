package com.duynghia.Academic.Management.System.identity.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LectureCreationRequest {
    @NotBlank(message = "DEPARTMENT_ID_REQUIRED")
    @Size(max = 50, message = "DEPARTMENT_ID_INVALID_LENGTH")
    String departmentId; // MaBoMon

    String academicTitle; // HocHam

    String degree; // HocVi

    String position; // ChucDanh

    @Valid
    @NotNull(message = "USER_INFO_REQUIRED")
    UserCreationRequest user;

    @NotBlank(message = "LAST_NAME_REQUIRED")
    @Size(max = 50, message = "INVALID_LAST_NAME")
    String lastName; // HoDem

    @NotBlank(message = "FIRST_NAME_REQUIRED")
    @Size(max = 50, message = "INVALID_FIRST_NAME")
    String firstName; // Ten

    @NotNull(message = "DOB_REQUIRED")
    @Past(message = "DOB_MUST_BE_IN_PAST")
    LocalDate dateOfBirth; // NgaySinh

    @NotBlank(message = "ADDRESS_REQUIRED")
    String address; // DiaChi

    @NotBlank(message = "PHONE_REQUIRED")
    @Pattern(regexp = "^(0|\\+84)[0-9]{9}$", message = "INVALID_PHONE_NUMBER")
    String phoneNumber; // DienThoai
}
