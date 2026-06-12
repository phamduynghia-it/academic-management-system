package com.duynghia.Academic.Management.System.identity.service.impl;

import com.duynghia.Academic.Management.System.academic.entities.Department;
import com.duynghia.Academic.Management.System.academic.repository.DepartmentRepository;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.dto.request.LectureCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.LectureResponse;
import com.duynghia.Academic.Management.System.identity.entities.Lecturer;
import com.duynghia.Academic.Management.System.identity.entities.User;
import com.duynghia.Academic.Management.System.identity.mapper.LecturerMapper;
import com.duynghia.Academic.Management.System.identity.repository.LecturerRepository;
import com.duynghia.Academic.Management.System.identity.service.ILectureService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LectureService implements ILectureService {
    LecturerRepository lecturerRepository;
    DepartmentRepository departmentRepository;
    UserService userService;
    LecturerMapper lecturerMapper;

    @Transactional
    @Override
    public LectureResponse createLecture(LectureCreationRequest request) {
        if (lecturerRepository.existsById(request.getUser().getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userService.createUser(request.getUser());

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
        Lecturer lecture = lecturerMapper.toLecture(request);
        lecture.setDepartment(department);
        lecture.setUser(user);
        lecture.setLecturerId(request.getUser().getUsername());
        lecturerRepository.save(lecture);
        return lecturerMapper.toLectureResponse(lecture);
    }
}
