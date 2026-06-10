package com.duynghia.Academic.Management.System.identity.service.impl;

import com.duynghia.Academic.Management.System.academic.entities.Program;
import com.duynghia.Academic.Management.System.academic.entities.StudentClass;
import com.duynghia.Academic.Management.System.academic.repository.ProgramRepository;
import com.duynghia.Academic.Management.System.academic.repository.StudentClassRepository;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.dto.request.StudentCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.StudentResponse;
import com.duynghia.Academic.Management.System.identity.entities.Student;
import com.duynghia.Academic.Management.System.identity.entities.User;
import com.duynghia.Academic.Management.System.identity.mapper.StudentMapper;
import com.duynghia.Academic.Management.System.identity.repository.StudentRepository;
import com.duynghia.Academic.Management.System.identity.service.IStudentService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService implements IStudentService {
    StudentRepository studentRepository;
    StudentMapper studentMapper;
    UserService userService;
    ProgramRepository programRepository;
    StudentClassRepository studentClassRepository;


    @Override
    @Transactional
    public StudentResponse createStudent(StudentCreationRequest request) {
        if (studentRepository.existsById(request.getUser().getUsername())) {
            throw new AppException(ErrorCode.STUDENT_EXISTED);
        }

        // 2. Tạo User (Nếu lỗi trùng username, Exception văng ra -> Dừng luôn)
        User savedUser = userService.createUser(request.getUser());
        Program program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new AppException(ErrorCode.PROGRAM_NOT_FOUND));
        StudentClass studentClass = studentClassRepository.findById(request.getStudentClassId())
                .orElseThrow(() -> new AppException(ErrorCode.CLASS_NOT_FOUND));
        // 3. Tạo Sinh viên
        Student student = Student.builder()
                .studentId(request.getUser().getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .address(request.getAddress())
                .cohort(request.getCohort())
                .phoneNumber(request.getPhoneNumber())
                .user(savedUser)
                .studentClass(studentClass)
                .program(program)
                .build();

        student = studentRepository.save(student);

        // 4. Trả về DTO
        return studentMapper.toStudentResponse(student);
    }
}

