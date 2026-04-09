package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCreationRequest;
import com.duynghia.Academic.Management.System.academic.entities.Faculty;
import com.duynghia.Academic.Management.System.academic.entities.Program;
import com.duynghia.Academic.Management.System.academic.mapper.ProgramMapper;
import com.duynghia.Academic.Management.System.academic.repository.FacultyRepository;
import com.duynghia.Academic.Management.System.academic.repository.ProgramRepository;
import com.duynghia.Academic.Management.System.academic.service.IProgramService;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProgramService implements IProgramService {
    ProgramRepository programRepository;
    ProgramMapper programMapper;
    FacultyRepository facultyRepository;

    @Override
    public Program createProgram(ProgramCreationRequest request) {
        Program program = programMapper.toProgram(request);
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new AppException(ErrorCode.FACULTY_NOT_FOUND));
        program.setFaculty(faculty);
        return programRepository.save(program);
    }

}
