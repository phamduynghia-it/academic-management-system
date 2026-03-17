package com.duynghia.Academic.Management.System.service;

import com.duynghia.Academic.Management.System.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.dto.response.UserResponse;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);
}
