package com.duynghia.Academic.Management.System.identity.dto.request;

import com.duynghia.Academic.Management.System.identity.enums.RoleName;
import com.duynghia.Academic.Management.System.identity.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @NotBlank(message = "EMAIL_REQUIRED")
    @Email(message = "INVALID_EMAIL")
    String email;

    @NotNull(message = "ROLE_REQUIRED")
    RoleName roleName;

    @NotBlank(message = "FULLNAME_REQUIRED")
    String fullName;

    @NotNull(message = "STATUS_REQUIRED")
    UserStatus status;

    StudentProfileUpdateRequest studentProfile;

    LecturerProfileUpdateRequest lecturerProfile;

}
