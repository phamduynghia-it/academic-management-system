package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.response.CourseSectionResponse;
import com.duynghia.Academic.Management.System.academic.entities.CourseSection;
import com.duynghia.Academic.Management.System.academic.mapper.CourseSectionMapper;
import com.duynghia.Academic.Management.System.academic.repository.CourseSectionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseSectionQueryService {
    CourseSectionRepository courseSectionRepository;
    CourseSectionMapper courseSectionMapper;

//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class CourseSectionListWrapper implements Serializable {
//        private List<CourseSectionResponse> data;
//    }


    // @Transactional(readOnly = true)
    @Cacheable(value = "base_sections", key = "#programId + '_' + #cohort")
    public List<CourseSectionResponse> getCachedBaseSections(String programId, String cohort) {
        List<CourseSection> sections = courseSectionRepository.findBaseSectionsForCohort(programId, cohort, LocalDateTime.now());

        return sections.stream()
                .map(courseSectionMapper::toCourseSectionResponse)
                .collect(Collectors.toList());

    }

}
