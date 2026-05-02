package com.duynghia.Academic.Management.System.identity.service.impl;

import com.duynghia.Academic.Management.System.academic.repository.DepartmentRepository;
import com.duynghia.Academic.Management.System.academic.repository.ProgramRepository;
import com.duynghia.Academic.Management.System.academic.repository.StudentClassRepository;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.dto.request.UserCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.request.UserUpdateRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.UserResponse;
import com.duynghia.Academic.Management.System.identity.entities.Lecturer;
import com.duynghia.Academic.Management.System.identity.entities.Role;
import com.duynghia.Academic.Management.System.identity.entities.Student;
import com.duynghia.Academic.Management.System.identity.entities.User;
import com.duynghia.Academic.Management.System.identity.enums.RoleName;
import com.duynghia.Academic.Management.System.identity.enums.UserStatus;
import com.duynghia.Academic.Management.System.identity.mapper.LecturerMapper;
import com.duynghia.Academic.Management.System.identity.mapper.StudentMapper;
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
import org.springframework.util.StringUtils;

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
    ProgramRepository programRepository;
    PasswordEncoder passwordEncoder;
    LecturerMapper lecturerMapper;
    StudentMapper studentMapper;

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
            var program = programRepository.findById(request.getProgramId())
                    .orElseThrow(() -> new AppException(ErrorCode.PROGRAM_NOT_FOUND));
            var studentClass = studentClassRepository.findById(request.getClassId())
                    .orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_FOUND));
            Student student = Student.builder()
                    .studentId(request.getUsername())
                    .programId(program.getProgramId())
                    .user(user)
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
                    .department(department)
                    .build();
            lecturerRepository.save(lecturer);
        }
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(String userId, UserUpdateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        Role role = roleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        userRepository.save(user);


        RoleName currentRole = user.getRole().getRoleName();

        if (currentRole == RoleName.STUDENT && request.getStudentProfile() != null) {
            Student student = studentRepository.findById(user.getUsername())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

            var stuRequest = request.getStudentProfile();


            studentMapper.updateStudent(student, stuRequest);

            if (StringUtils.hasText(stuRequest.getClassId())) {
                var stuClass = studentClassRepository.findById(stuRequest.getClassId())
                        .orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_FOUND));
                student.setStudentClass(stuClass);
            }
            studentRepository.save(student);

        } else if (currentRole == RoleName.LECTURER && request.getLecturerProfile() != null) {
            Lecturer lecturer = lecturerRepository.findById(user.getUsername())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

            var lecRequest = request.getLecturerProfile();

            lecturerMapper.updateLecturer(lecturer, lecRequest);

            if (StringUtils.hasText(lecRequest.getDepartmentId())) {
                var dept = departmentRepository.findById(lecRequest.getDepartmentId())
                        .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
                lecturer.setDepartment(dept);
            }
            lecturerRepository.save(lecturer);
        }
        return userMapper.toUserResponse(user);
    }
}
