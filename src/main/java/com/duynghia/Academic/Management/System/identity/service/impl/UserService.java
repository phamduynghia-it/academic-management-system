package com.duynghia.Academic.Management.System.identity.service.impl;

import com.duynghia.Academic.Management.System.academic.repository.DepartmentRepository;
import com.duynghia.Academic.Management.System.academic.repository.StudentClassRepository;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.UserResponse;
import com.duynghia.Academic.Management.System.identity.entities.Lecturer;
import com.duynghia.Academic.Management.System.identity.entities.Role;
import com.duynghia.Academic.Management.System.identity.entities.Student;
import com.duynghia.Academic.Management.System.identity.entities.User;
import com.duynghia.Academic.Management.System.identity.enums.RoleName;
import com.duynghia.Academic.Management.System.identity.enums.UserStatus;
import com.duynghia.Academic.Management.System.identity.mapper.UserMapper;
import com.duynghia.Academic.Management.System.identity.repository.LecturerRepository;
import com.duynghia.Academic.Management.System.identity.repository.RoleRepository;
import com.duynghia.Academic.Management.System.identity.repository.StudentRepository;
import com.duynghia.Academic.Management.System.identity.repository.UserRepository;
import com.duynghia.Academic.Management.System.identity.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;
    LecturerRepository lecturerRepository;
    StudentRepository studentRepository;
    StudentClassRepository studentClassRepository;
    DepartmentRepository departmentRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse)
                .toList();
    }

    @Transactional
    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        RoleName roleName = RoleName.valueOf(request.getRole());
        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        user.setRole(role);
        user = userRepository.save(user);


        if (user.getRole().getRoleName() == RoleName.STUDENT) {
            if (request.getClassId() == null || request.getClassId().trim().isEmpty()) {
                throw new AppException(ErrorCode.CLASS_ID_REQUIRED);
            }
            if (request.getProgramId() == null || request.getProgramId().trim().isEmpty()) {
                throw new AppException(ErrorCode.PROGRAM_REQUIRED);
            }
            var studentClass = studentClassRepository.findById(request.getClassId())
                    .orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_FOUND));

            Student student = Student.builder()
                    .studentId(request.getUsername())
                    .user(user)
                    .fullName(request.getFullName())
                    .studentClass(studentClass)
                    .build();
            studentRepository.save(student);

        } else if (user.getRole().getRoleName() == RoleName.LECTURER) {
            if (request.getDepartmentId() == null || request.getDepartmentId().trim().isEmpty()) {
                throw new AppException(ErrorCode.DEPARTMENT_ID_REQUIRED);
            }

            var department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));

            Lecturer lecturer = Lecturer.builder()
                    .lecturerId(request.getUsername())
                    .user(user)
                    .fullName(request.getFullName())
                    .department(department)
                    .build();
            lecturerRepository.save(lecturer);
        }

        return userMapper.toUserResponse(user);
    }

}
