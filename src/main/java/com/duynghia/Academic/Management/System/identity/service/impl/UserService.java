package com.duynghia.Academic.Management.System.identity.service.impl;

import com.duynghia.Academic.Management.System.identity.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.UserResponse;
import com.duynghia.Academic.Management.System.identity.entities.Role;
import com.duynghia.Academic.Management.System.identity.entities.User;
import com.duynghia.Academic.Management.System.identity.enums.UserStatus;
import com.duynghia.Academic.Management.System.identity.exception.AppException;
import com.duynghia.Academic.Management.System.identity.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.mapper.UserMapper;
import com.duynghia.Academic.Management.System.identity.repository.RoleRepository;
import com.duynghia.Academic.Management.System.identity.repository.UserRepository;
import com.duynghia.Academic.Management.System.identity.service.IUserService;
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
    RoleRepository roleRepository;

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        user.setStatus(UserStatus.ACTIVE);
        Role role = roleRepository.findByRoleName(request.getRole().name())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        user.setRole(role);
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
