package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseSectionResponse;
import com.duynghia.Academic.Management.System.academic.entities.Course;
import com.duynghia.Academic.Management.System.academic.entities.CourseSection;
import com.duynghia.Academic.Management.System.academic.entities.RegistrationPeriod;
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
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseSectionService implements ICourseSectionService {
    CourseSectionRepository courseSectionRepository;
    CourseSectionMapper courseSectionMapper;
    LecturerRepository lecturerRepository;
    CourseRepository courseRepository;
    StudentRepository studentRepository;
    RegistrationPeriodRepository registrationPeriodRepository;
    StudentCourseCacheService studentCourseCacheService;
    CourseSectionQueryService courseSectionQueryService;
    CourseConditionCacheService courseConditionCacheService;
    ProgramCourseCacheService programCourseCacheService;

    @Transactional
    @Override
    public CourseSectionResponse createCourseSection(CourseSectionCreationRequest request) {
        if (courseSectionRepository.existsById(request.getSectionId())) {

            throw new AppException(ErrorCode.SECTION_EXISTED);
        }
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new AppException(ErrorCode.INVALID_DATE_RANGE);
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
                                                              String academicYear, Integer semester, Integer phase, CourseSectionStatus status, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

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
    public PageResponse<CourseSectionResponse> getAvailableSections(int page, int size, String sortBy, String
            sortDir) {
        String currentStudentId = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentRepository.findById(currentStudentId)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

        String cohort = student.getCohort();
        String programId = student.getProgram().getProgramId();
        LocalDateTime now = LocalDateTime.now();

        RegistrationPeriod activePeriod = registrationPeriodRepository.findActivePeriodForStudent(cohort, now)
                .orElseThrow(() -> new AppException(ErrorCode.NO_ACTIVE_REGISTRATION_PERIOD));

        int systemYear = Integer.parseInt(activePeriod.getAcademicYear().substring(0, 4));
        int systemTerm = activePeriod.getSemester();

        student.calculateAndSetCurrentSemester(systemYear, systemTerm);
        int studentCurrentSemester = student.getCurrentSemester();

        // 2. Tạo Pageable từ Request
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        // 3. Lấy dữ liệu từ Cache (O(1) Siêu nhanh)
        // CourseSectionQueryService.CourseSectionListWrapper wrapper =
        List<CourseSectionResponse> allBaseSections = courseSectionQueryService.getCachedBaseSections(programId, cohort);
        List<String> ignoredCourseIds = studentCourseCacheService.getPassedOrRegisteredCourseIds(currentStudentId);
        List<String> passedCourseIds = studentCourseCacheService.getPassedCourseIds(currentStudentId);
        Map<String, List<String>> prerequisitesMap = courseConditionCacheService.getCachedPrerequisitesMap();
        Map<String, Integer> programSemesterMap = programCourseCacheService.getCachedProgramSemesterMap(programId);

        // 4. Lọc danh sách trên RAM
        List<CourseSectionResponse> eligibleSections = allBaseSections.stream()
                .filter(section -> {
                    String courseId = section.getCourseId();

                    // ĐIỀU KIỆN 1: Bỏ qua nếu đã học (PASSED) hoặc đang đăng ký (REGISTERED)
                    if (ignoredCourseIds.contains(courseId)) {
                        return false;
                    }

                    // ĐIỀU KIỆN 2: Kì học của môn (theo CTĐT) <= Kì hiện tại của sinh viên
                    Integer courseSemesterInProgram = programSemesterMap.get(courseId);
                    if (courseSemesterInProgram != null && courseSemesterInProgram > studentCurrentSemester) {
                        return false;
                    }

                    // ĐIỀU KIỆN 3: Phải PASS tất cả các môn tiên quyết
                    List<String> requiredCourses = prerequisitesMap.get(courseId);
                    if (requiredCourses != null && !requiredCourses.isEmpty()) {
                        for (String reqCourseId : requiredCourses) {
                            if (!passedCourseIds.contains(reqCourseId)) {
                                return false;
                            }
                        }
                    }

                    return true;
                })
                .collect(Collectors.toList());

        // 5. Sắp xếp in-memory
        eligibleSections.sort((s1, s2) -> {
            int compareResult = s1.getSectionName().compareToIgnoreCase(s2.getSectionName());
            return sortDir.equalsIgnoreCase("asc") ? compareResult : -compareResult;
        });

        // 6. Phân trang bằng PageImpl của Spring
        int start = (int) pageable.getOffset();
        List<CourseSectionResponse> pageContent;

        if (start >= eligibleSections.size()) {
            pageContent = List.of();
        } else {
            int end = Math.min((start + pageable.getPageSize()), eligibleSections.size());
            pageContent = eligibleSections.subList(start, end);
        }

        Page<CourseSectionResponse> pagedSections = new PageImpl<>(pageContent, pageable, eligibleSections.size());

        // 8. Trả về kết quả
        return PageResponse.<CourseSectionResponse>builder()
                .currentPage(page)
                .totalPages(pagedSections.getTotalPages())
                .totalElements(pagedSections.getTotalElements())
                .pageSize(size)
                .data(pagedSections.getContent())
                .build();
    }
}

