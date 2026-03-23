package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.FacultyCreationRequest;
import com.duynghia.Academic.Management.System.academic.entities.Faculty;
import com.duynghia.Academic.Management.System.academic.mapper.FacultyMapper;
import com.duynghia.Academic.Management.System.academic.repository.FacultyRepository;
import com.duynghia.Academic.Management.System.academic.service.IFacultyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacultyService implements IFacultyService {
    FacultyRepository facultyRepository;
    FacultyMapper facultyMapper;

    @Override
    public Faculty createFaculty(FacultyCreationRequest request) {
        Faculty faculty = facultyMapper.toFaculty(request);
        return facultyRepository.save(faculty);
    }
}
