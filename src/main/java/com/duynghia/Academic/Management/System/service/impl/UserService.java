package com.duynghia.Academic.Management.System.service.impl;

import com.duynghia.Academic.Management.System.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.dto.response.UserResponse;
import com.duynghia.Academic.Management.System.entities.User;
import com.duynghia.Academic.Management.System.enums.UserStatus;
import com.duynghia.Academic.Management.System.mapper.UserMapper;
import com.duynghia.Academic.Management.System.repository.UserRepository;
import com.duynghia.Academic.Management.System.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setStatus(UserStatus.ACTIVE);
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
