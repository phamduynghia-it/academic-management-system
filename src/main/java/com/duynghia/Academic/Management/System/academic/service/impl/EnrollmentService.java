package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.entities.*;
import com.duynghia.Academic.Management.System.academic.enums.EnrollmentStatus;
import com.duynghia.Academic.Management.System.academic.repository.CourseSectionRepository;
import com.duynghia.Academic.Management.System.academic.repository.EnrollmentRepository;
import com.duynghia.Academic.Management.System.academic.repository.RegistrationPeriodRepository;
import com.duynghia.Academic.Management.System.academic.repository.StudentSemesterCreditRepository;
import com.duynghia.Academic.Management.System.academic.service.IEnrollmentService;
import com.duynghia.Academic.Management.System.common.AppConstants;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.entities.Student;
import com.duynghia.Academic.Management.System.identity.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EnrollmentService implements IEnrollmentService {

    EnrollmentRepository enrollmentRepository;
    CourseSectionRepository courseSectionRepository;
    StudentRepository studentRepository;
    StudentSemesterCreditRepository studentSemesterCreditRepository;
    RegistrationPeriodRepository registrationPeriodRepository;
    StudentCourseCacheService studentCourseCacheService;
    CourseConditionCacheService courseConditionCacheService;
    ProgramCourseCacheService programCourseCacheService;

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5, backoff = @Backoff(delay = 50))
    public void registerCourseSection(String sectionId) {
        String studentId = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

        CourseSection section = courseSectionRepository.findById(sectionId)
                .orElseThrow(() -> new AppException(ErrorCode.SECTION_NOT_FOUND));

        CourseSectionStudentId id = new CourseSectionStudentId(sectionId, studentId);

        if (enrollmentRepository.existsById(id)) {
            throw new AppException(ErrorCode.ALREADY_REGISTERED);
        }

        if (section.getCurrentEnrollment() >= section.getMaxCapacity()) {
            throw new AppException(ErrorCode.SECTION_IS_FULL);
        }

        LocalDateTime now = LocalDateTime.now();
        RegistrationPeriod activePeriod = registrationPeriodRepository.findActivePeriodForStudent(student.getCohort(), now)
                .orElseThrow(() -> new AppException(ErrorCode.NO_ACTIVE_REGISTRATION_PERIOD));

        String courseId = section.getCourse().getCourseId();
        String programId = student.getProgram().getProgramId();

        // Validation 1: Check if already passed or registered for this course
        List<String> ignoredCourseIds = studentCourseCacheService.getPassedOrRegisteredCourseIds(studentId);
        if (ignoredCourseIds.contains(courseId)) {
            throw new AppException(ErrorCode.STUDENT_ALREADY_IN_SECTION);
        }

        // Calculate student current semester
        int systemYear = Integer.parseInt(activePeriod.getAcademicYear().substring(0, 4));
        int systemTerm = activePeriod.getSemester();
        student.calculateAndSetCurrentSemester(systemYear, systemTerm);
        int studentCurrentSemester = student.getCurrentSemester();

        // Validation 2: Check if course is in program and semester is allowed
        Map<String, Integer> programSemesterMap = programCourseCacheService.getCachedProgramSemesterMap(programId);
        Integer courseSemesterInProgram = programSemesterMap.get(courseId);
        if (courseSemesterInProgram == null) {
            throw new AppException(ErrorCode.COURSE_NOT_EXISTED_IN_PROGRAM);
        }
        if (courseSemesterInProgram > studentCurrentSemester) {
            // Reusing ErrorCode or add a specific one, using PREREQUISITE_NOT_MET for now or create new
            throw new AppException(ErrorCode.INVALID_SEMESTER);
        }

        // Validation 3: Check prerequisites
        Map<String, List<String>> prerequisitesMap = courseConditionCacheService.getCachedPrerequisitesMap();
        List<String> requiredCourses = prerequisitesMap.get(courseId);
        if (requiredCourses != null && !requiredCourses.isEmpty()) {
            List<String> passedCourseIds = studentCourseCacheService.getPassedCourseIds(studentId);
            for (String reqCourseId : requiredCourses) {
                if (!passedCourseIds.contains(reqCourseId)) {
                    throw new AppException(ErrorCode.PREREQUISITE_NOT_MET);
                }
            }
        }

        String academicYear = activePeriod.getAcademicYear();
        Integer semester = activePeriod.getSemester();

        Integer courseCredits = section.getCourse().getCredits();

        StudentSemesterCredit creditRecord = studentSemesterCreditRepository.findByStudent_StudentIdAndAcademicYearAndSemester(studentId, academicYear, semester)
                .orElse(StudentSemesterCredit.builder()
                        .student(student)
                        .academicYear(academicYear)
                        .semester(semester)
                        .totalRegisteredCredits(0)
                        .build());

        int newTotalCredits = creditRecord.getTotalRegisteredCredits() + courseCredits;
        if (newTotalCredits > AppConstants.MAX_CREDITS_PER_SEMESTER) {
            throw new AppException(ErrorCode.MAX_CREDITS_EXCEEDED);
        }

        creditRecord.setTotalRegisteredCredits(newTotalCredits);
        studentSemesterCreditRepository.save(creditRecord);

        section.setCurrentEnrollment(section.getCurrentEnrollment() + 1);
        courseSectionRepository.saveAndFlush(section);

        CourseSectionStudent enrollment = CourseSectionStudent.builder()
                .id(id)
                .courseSection(section)
                .student(student)
                .status(EnrollmentStatus.REGISTERED)
                .build();
        enrollmentRepository.save(enrollment);
        
        // Invalidate cache
        studentCourseCacheService.evictStudentCourseCache(studentId);
    }

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5, backoff = @Backoff(delay = 50))
    public void cancelCourseSection(String sectionId) {
        String studentId = SecurityContextHolder.getContext().getAuthentication().getName();
        CourseSectionStudentId id = new CourseSectionStudentId(sectionId, studentId);

        CourseSectionStudent enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_IN_SECTION));

        CourseSection section = enrollment.getCourseSection();
        Student student = enrollment.getStudent();

        LocalDateTime now = LocalDateTime.now();
        RegistrationPeriod activePeriod = registrationPeriodRepository.findActivePeriodForStudent(student.getCohort(), now)
                .orElseThrow(() -> new AppException(ErrorCode.NO_ACTIVE_REGISTRATION_PERIOD));

        String academicYear = activePeriod.getAcademicYear();
        Integer semester = activePeriod.getSemester();

        Integer courseCredits = section.getCourse().getCredits();

        StudentSemesterCredit creditRecord = studentSemesterCreditRepository.findByStudent_StudentIdAndAcademicYearAndSemester(studentId, academicYear, semester)
                .orElseThrow(() -> new AppException(ErrorCode.CREDIT_RECORD_NOT_FOUND));

        int newTotalCredits = creditRecord.getTotalRegisteredCredits() - courseCredits;

        creditRecord.setTotalRegisteredCredits(Math.max(0, newTotalCredits));
        studentSemesterCreditRepository.save(creditRecord);

        section.setCurrentEnrollment(Math.max(0, section.getCurrentEnrollment() - 1));
        courseSectionRepository.saveAndFlush(section);

        enrollmentRepository.delete(enrollment);
        
        // Invalidate cache
        studentCourseCacheService.evictStudentCourseCache(studentId);
    }
}
