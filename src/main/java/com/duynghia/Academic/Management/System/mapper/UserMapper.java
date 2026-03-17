package com.duynghia.Academic.Management.System.mapper;

import com.duynghia.Academic.Management.System.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.dto.response.UserResponse;
import com.duynghia.Academic.Management.System.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);
}
