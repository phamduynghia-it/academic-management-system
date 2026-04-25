package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.CourseSectionUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.CourseSectionResponse;

public interface ICourseSectionService {
    CourseSectionResponse createCourseSection(CourseSectionCreationRequest request);

    CourseSectionResponse updateCourseSection(String courseSectionId, CourseSectionUpdateRequest request);
}
