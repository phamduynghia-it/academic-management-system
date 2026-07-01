package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.entities.CourseCondition;
import com.duynghia.Academic.Management.System.academic.repository.CourseConditionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseConditionCacheService {
    CourseConditionRepository courseConditionRepository;


    @Cacheable(value = "all_prerequisites", key = "'map'")
    public Map<String, List<String>> getCachedPrerequisitesMap() {
        List<CourseCondition> conditions = courseConditionRepository.findAll();

        return conditions.stream().collect(Collectors.groupingBy(
                c -> c.getCourse().getCourseId(),
                Collectors.mapping(c -> c.getRequiredCourse().getCourseId(), Collectors.toList())
        ));
    }
}
