package com.duynghia.Academic.Management.System.identity.mapper;

import com.duynghia.Academic.Management.System.identity.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.UserResponse;
import com.duynghia.Academic.Management.System.identity.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    User toUser(UserCreationRequest request);

    @Mapping(source = "role.roleName", target = "roleName")
    UserResponse toUserResponse(User user);
}
