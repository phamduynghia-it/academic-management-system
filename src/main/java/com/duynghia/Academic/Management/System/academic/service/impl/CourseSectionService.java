package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseSectionResponse;
import com.duynghia.Academic.Management.System.academic.entities.Course;
import com.duynghia.Academic.Management.System.academic.entities.CourseSection;
import com.duynghia.Academic.Management.System.academic.mapper.CourseSectionMapper;
import com.duynghia.Academic.Management.System.academic.repository.CourseRepository;
import com.duynghia.Academic.Management.System.academic.repository.CourseSectionRepository;
import com.duynghia.Academic.Management.System.academic.service.ICourseSectionService;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.entities.Lecturer;
import com.duynghia.Academic.Management.System.identity.repository.LecturerRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseSectionService implements ICourseSectionService {
    CourseSectionRepository courseSectionRepository;
    CourseSectionMapper courseSectionMapper;
    LecturerRepository lecturerRepository;
    CourseRepository courseRepository;

    @Transactional
    @Override
    public CourseSectionResponse createCourseSection(CourseSectionCreationRequest request) {
        CourseSection courseSection = courseSectionMapper.toCourseSection(request);
        if (request.getLecturerId() != null && !request.getLecturerId().trim().isEmpty()) {
            Lecturer lecturer = lecturerRepository.findById(request.getLecturerId())
                    .orElseThrow(() -> new AppException(ErrorCode.LECTURER_NOT_FOUND));
            courseSection.setLecturer(lecturer);
        }
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        courseSection.setCourse(course);
        return courseSectionMapper.toCourseSectionResponse(courseSectionRepository.save(courseSection));
    }

    @Transactional
    @Override
    public CourseSectionResponse updateCourseSection(String courseSectionId, CourseSectionUpdateRequest request) {
        CourseSection courseSection = courseSectionRepository.findById(courseSectionId)
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        courseSectionMapper.updateCourseSection(courseSection, request);
        if (request.getLecturerId() != null && !request.getLecturerId().trim().isEmpty()) {
            Lecturer lecturer = lecturerRepository.findById(request.getLecturerId())
                    .orElseThrow(() -> new AppException(ErrorCode.LECTURER_NOT_FOUND));
            courseSection.setLecturer(lecturer);
        }
        return courseSectionMapper.toCourseSectionResponse(courseSectionRepository.save(courseSection));
    }
}
