package com.duynghia.Academic.Management.System.identity.service;

import com.duynghia.Academic.Management.System.identity.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.UserResponse;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);
}
