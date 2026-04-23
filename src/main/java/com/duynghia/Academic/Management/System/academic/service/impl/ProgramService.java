package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCourseRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.ProgramUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramCourseResponse;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramResponse;
import com.duynghia.Academic.Management.System.academic.dto.response.ProgramWithCoursesResponse;
import com.duynghia.Academic.Management.System.academic.entities.*;
import com.duynghia.Academic.Management.System.academic.mapper.ProgramCourseMapper;
import com.duynghia.Academic.Management.System.academic.mapper.ProgramMapper;
import com.duynghia.Academic.Management.System.academic.repository.CourseRepository;
import com.duynghia.Academic.Management.System.academic.repository.FacultyRepository;
import com.duynghia.Academic.Management.System.academic.repository.ProgramCourseRepository;
import com.duynghia.Academic.Management.System.academic.repository.ProgramRepository;
import com.duynghia.Academic.Management.System.academic.service.IProgramService;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProgramService implements IProgramService {

    ProgramRepository programRepository;
    FacultyRepository facultyRepository;
    ProgramMapper programMapper;
    ProgramCourseMapper programCourseMapper;
    CourseRepository courseRepository;
    ProgramCourseRepository programCourseRepository;

    @Override
    public ProgramResponse createProgram(ProgramCreationRequest request) {
        if (programRepository.existsById(request.getProgramId())) {
            throw new AppException(ErrorCode.PROGRAM_EXISTED); // Thêm mã lỗi này vào Enum
        }

        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new AppException(ErrorCode.FACULTY_NOT_FOUND));

        Program program = programMapper.toProgram(request);
        program.setFaculty(faculty);

        return programMapper.toProgramResponse(programRepository.save(program));
    }

    @Override
    public List<ProgramResponse> getAllPrograms() {
        return programRepository.findAll()
                .stream()
                .map(programMapper::toProgramResponse)
                .toList();
    }

    @Override
    public ProgramResponse getProgramById(String id) {
        return programRepository.findById(id)
                .map(programMapper::toProgramResponse)
                .orElseThrow(() -> new AppException(ErrorCode.PROGRAM_NOT_FOUND)); // Thêm lỗi vào Enum
    }

    @Override
    public ProgramResponse updateProgram(String id, ProgramUpdateRequest request) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROGRAM_NOT_FOUND));


        programMapper.updateProgramFromRequest(program, request);

        return programMapper.toProgramResponse(programRepository.save(program));
    }

    @Override
    public void deleteProgram(String id) {
        if (!programRepository.existsById(id)) {
            throw new AppException(ErrorCode.PROGRAM_NOT_FOUND);
        }
        programRepository.deleteById(id);
    }

    @Transactional
    public ProgramCourseResponse addCourseToProgram(String programId, ProgramCourseRequest request) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new AppException(ErrorCode.PROGRAM_NOT_FOUND));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        ProgramCourseId id = new ProgramCourseId(programId, request.getCourseId());
        if (programCourseRepository.existsById(id))
            throw new AppException(ErrorCode.COURSE_ALREADY_IN_PROGRAM);

        ProgramCourse programCourse = ProgramCourse.builder()
                .id(id)
                .program(program)
                .course(course)
                .semester(request.getSemester())
                .build();

        return programCourseMapper.toResponse(programCourseRepository.save(programCourse));
    }

    @Override
    public ProgramWithCoursesResponse getProgramDetails(String programId) {

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new AppException(ErrorCode.PROGRAM_NOT_FOUND));

        return programMapper.toProgramWithCoursesResponse(program);
    }

    @Transactional
    @Override
    public void deleteCourseInProgram(String programId, String courseId) {
        ProgramCourseId programCourseId = new ProgramCourseId(programId, courseId);
        ProgramCourse programCourse = programCourseRepository.findById(programCourseId)
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_EXISTED_IN_PROGRAM));
        programCourseRepository.delete(programCourse);
    }
}
