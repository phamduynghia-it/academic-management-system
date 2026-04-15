package com.duynghia.Academic.Management.System.identity.service;

import com.duynghia.Academic.Management.System.identity.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getAllUser();
}
