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
public class StudentCreationRequest {

    @Valid
    @NotNull(message = "USER_INFO_REQUIRED")
    UserCreationRequest user;

    @NotBlank(message = "LAST_NAME_REQUIRED")
    @Size(max = 50, message = "INVALID_LAST_NAME")
    String lastName; // HoDem

    @NotBlank(message = "FIRST_NAME_REQUIRED")
    @Size(max = 50, message = "INVALID_FIRST_NAME")
    String firstName; // Ten

    @NotBlank(message = "PROGRAM_ID_REQUIRED")
    String programId; // MaCTDT

    @NotBlank(message = "CLASS_ID_REQUIRED")
    String studentClassId; // MaLop

    @NotNull(message = "DOB_REQUIRED")
    @Past(message = "DOB_MUST_BE_IN_PAST")
    LocalDate dateOfBirth; // NgaySinh

    @NotBlank(message = "ADDRESS_REQUIRED")
    String address; // DiaChi

    @NotBlank(message = "COHORT_REQUIRED")
    String cohort; // DiaChi

    @NotBlank(message = "PHONE_REQUIRED")
    @Pattern(regexp = "^(0|\\+84)[0-9]{9}$", message = "INVALID_PHONE_NUMBER") // Validate số điện thoại chuẩn Việt Nam (09xxx hoặc +849xxx)
    String phoneNumber; // DienThoai

}