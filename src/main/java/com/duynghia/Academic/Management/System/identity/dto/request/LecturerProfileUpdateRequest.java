package com.duynghia.Academic.Management.System.identity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LecturerProfileUpdateRequest {
    @NotBlank(message = "DEPARTMENT_ID_REQUIRED")
    String departmentId;

    String academicTitle;

    String degree;

    String position;
}
