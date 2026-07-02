package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.entities.ProgramCourse;
import com.duynghia.Academic.Management.System.academic.repository.ProgramCourseRepository;
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
public class ProgramCourseCacheService {
    ProgramCourseRepository programCourseRepository;

    // Trả về Map<Mã Môn Học, Kì Học> (Ví dụ: "SE201" -> Kì 2, "SE202" -> Kì 4)
    @Cacheable(value = "program_semesters", key = "#programId")
    public Map<String, Integer> getCachedProgramSemesterMap(String programId) {
        List<ProgramCourse> programCourses = programCourseRepository.findByProgram_ProgramId(programId);

        return programCourses.stream().collect(Collectors.toMap(
                pc -> pc.getCourse().getCourseId(),
                ProgramCourse::getSemester,
                (existing, replacement) -> existing
        ));
    }
}
