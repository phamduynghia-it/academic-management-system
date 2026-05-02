package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseSectionResponse;
import com.duynghia.Academic.Management.System.academic.entities.Course;
import com.duynghia.Academic.Management.System.academic.entities.CourseSection;
import com.duynghia.Academic.Management.System.academic.enums.CourseSectionStatus;
import com.duynghia.Academic.Management.System.academic.mapper.CourseSectionMapper;
import com.duynghia.Academic.Management.System.academic.repository.CourseRepository;
import com.duynghia.Academic.Management.System.academic.repository.CourseSectionRepository;
import com.duynghia.Academic.Management.System.academic.repository.RegistrationPeriodRepository;
import com.duynghia.Academic.Management.System.academic.service.ICourseSectionService;
import com.duynghia.Academic.Management.System.common.PageResponse;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.entities.Lecturer;
import com.duynghia.Academic.Management.System.identity.entities.Student;
import com.duynghia.Academic.Management.System.identity.repository.LecturerRepository;
import com.duynghia.Academic.Management.System.identity.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseSectionService implements ICourseSectionService {
    CourseSectionRepository courseSectionRepository;
    CourseSectionMapper courseSectionMapper;
    LecturerRepository lecturerRepository;
    CourseRepository courseRepository;
    StudentRepository studentRepository; // Để lấy thông tin sinh viên
    RegistrationPeriodRepository registrationPeriodRepository;

    @Transactional
    @Override
    public CourseSectionResponse createCourseSection(CourseSectionCreationRequest request) {
        if (courseSectionRepository.existsById(request.getSectionId())) {

            throw new AppException(ErrorCode.SECTION_EXISTED);
        }
        CourseSection courseSection = courseSectionMapper.toCourseSection(request);
        if (request.getLecturerId() != null && !request.getLecturerId().trim().isEmpty()) {
            Lecturer lecturer = lecturerRepository.findById(request.getLecturerId())
                    .orElseThrow(() -> new AppException(ErrorCode.LECTURER_NOT_FOUND));
            courseSection.setLecturer(lecturer);
        }
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        courseSection.setCourse(course);
        courseSection.setStatus(CourseSectionStatus.PLANNED);
        return courseSectionMapper.toCourseSectionResponse(courseSectionRepository.save(courseSection));
    }

    @Transactional
    @Override
    public CourseSectionResponse updateCourseSection(String courseSectionId, CourseSectionUpdateRequest request) {
        CourseSection courseSection = courseSectionRepository.findById(courseSectionId)
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        if (request.getLecturerId() != null && !request.getLecturerId().trim().isEmpty()) {
            Lecturer lecturer = lecturerRepository.findById(request.getLecturerId())
                    .orElseThrow(() -> new AppException(ErrorCode.LECTURER_NOT_FOUND));
            courseSection.setLecturer(lecturer);
        }

        CourseSectionStatus oldStatus = courseSection.getStatus();
        CourseSectionStatus newStatus = request.getStatus();
        if (newStatus != null && oldStatus != newStatus) {

            //  Lớp đã HỦY thì cấm mọi thao tác đổi trạng thái
            if (oldStatus == CourseSectionStatus.CANCELLED) {
                throw new AppException(ErrorCode.CANNOT_UPDATE_CANCELLED_SECTION);
            }

            // Lớp ĐÃ KẾT THÚC thì cũng cấm đổi sang trạng thái khác
            if (oldStatus == CourseSectionStatus.COMPLETED) {
                throw new AppException(ErrorCode.CANNOT_UPDATE_COMPLETED_SECTION);
            }

            //  Lớp ĐANG HỌC thì cấm quay ngược về LÊN LỊCH hoặc bị HỦY
            if (oldStatus == CourseSectionStatus.ONGOING) {
                if (newStatus == CourseSectionStatus.PLANNED || newStatus == CourseSectionStatus.CANCELLED) {
                    throw new AppException(ErrorCode.INVALID_STATUS_TRANSITION);
                }
            }
        }
        courseSectionMapper.updateCourseSection(courseSection, request);
        return courseSectionMapper.toCourseSectionResponse(courseSectionRepository.save(courseSection));
    }

    @Override
    public PageResponse<CourseSectionResponse> getAllSections(int page, int size, String keyword,
                                                              String academicYear, Integer semester, Integer phase, CourseSectionStatus status) {
        // Sắp xếp mặc định theo ID mới nhất chẳng hạn
        Sort sort = Sort.by(Sort.Direction.DESC, "sectionId");

        Pageable pageable = PageRequest.of(page - 1, size, sort);


        Page<CourseSection> pageData = courseSectionRepository.searchAndFilterCourseSection(keyword, academicYear, semester, phase, status,
                pageable);


        List<CourseSectionResponse> dtoList = pageData.getContent().stream()
                .map(courseSectionMapper::toCourseSectionResponse)
                .toList();

        return PageResponse.<CourseSectionResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(dtoList)
                .build();
    }
@Override
    public PageResponse<CourseSectionResponse> getAvailableSections(int page, int size) {
        String currentStudentId = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentRepository.findById(currentStudentId)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

        LocalDateTime now = LocalDateTime.now();
        String cohort = student.getCohort();
        String programId = student.getProgramId();


        boolean hasActivePeriod = registrationPeriodRepository.existsActivePeriodForStudent(cohort, now);
        if (!hasActivePeriod) {
            throw new AppException(ErrorCode.NO_ACTIVE_REGISTRATION_PERIOD);
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "sectionId");

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<CourseSection> pagedSections = courseSectionRepository.findAvailableSectionsForStudent(
                programId,
                currentStudentId,
                cohort,
                now,
                pageable
        );


        List<CourseSectionResponse> dtoList = pagedSections.getContent().stream()
                .map(courseSectionMapper::toCourseSectionResponse)
                .collect(Collectors.toList());

        return PageResponse.<CourseSectionResponse>builder()

                .currentPage(page)
                .totalPages(pagedSections.getTotalPages())
                .totalElements(pagedSections.getTotalElements())
                .pageSize(size)
                .data(dtoList)
                .build();
    }
}
