package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseSectionResponse;
import com.duynghia.Academic.Management.System.academic.enums.CourseSectionStatus;
import com.duynghia.Academic.Management.System.common.PageResponse;

public interface ICourseSectionService {
    CourseSectionResponse createCourseSection(CourseSectionCreationRequest request);

    CourseSectionResponse updateCourseSection(String courseSectionId, CourseSectionUpdateRequest request);

    public PageResponse<CourseSectionResponse> getAllSections(int page, int size, String keyword,
                                                              String academicYear, Integer semester, Integer phase, CourseSectionStatus status);
}
