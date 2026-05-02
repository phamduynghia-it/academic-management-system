package com.duynghia.Academic.Management.System.identity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentProfileUpdateRequest {
    @Size(max = 25, message = "PROGRAM_ID_INVALID_LENGTH")
    @NotBlank(message = "PROGRAM_REQUIRED")
    String programId;
    @NotBlank(message = "CLASS_ID_REQUIRED")
    String classId;
}
