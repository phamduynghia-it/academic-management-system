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
public class AuthenticationRequest {
    @NotBlank(message = "USERNAME_REQUIRED")
    @Size(min = 2, max = 50, message = "INVALID_USERNAME")
    String username;
    @NotBlank(message = "PASSWORD_REQUIRED")
    @Size(min = 6, max = 255, message = "INVALID_PASSWORD")
    String password;
}
