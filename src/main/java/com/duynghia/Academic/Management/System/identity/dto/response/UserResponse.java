package com.duynghia.Academic.Management.System.identity.dto.response;

import com.duynghia.Academic.Management.System.identity.entities.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String username;
    String email;
    Role role;
}
