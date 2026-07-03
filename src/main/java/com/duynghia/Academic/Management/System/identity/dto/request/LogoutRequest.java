package com.duynghia.Academic.Management.System.identity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogoutRequest {
    @NotBlank(message = "Token is mandatory")
    String token;

    @NotBlank(message = "REFRESH_TOKEN_REQUIRED")
    String refreshToken;
}
