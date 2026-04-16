package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.FacultyCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.request.FacultyUpdateRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.FacultyResponse;
import com.duynghia.Academic.Management.System.academic.entities.Faculty;
import com.duynghia.Academic.Management.System.academic.mapper.FacultyMapper;
import com.duynghia.Academic.Management.System.academic.repository.FacultyRepository;
import com.duynghia.Academic.Management.System.academic.service.IFacultyService;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacultyService implements IFacultyService {
    FacultyRepository facultyRepository;
    FacultyMapper facultyMapper;

    @Override
    public FacultyResponse createFaculty(FacultyCreationRequest request) {
        Faculty faculty = facultyMapper.toFaculty(request);
        return facultyMapper.toFacultyResponse(facultyRepository.save(faculty));
    }

    @Override
    public List<FacultyResponse> getAllFaculties() {
        return facultyRepository.findAll()
                .stream()
                .map(facultyMapper::toFacultyResponse)
                .toList();
    }

    @Override
    public FacultyResponse getFacultyById(String id) {
        return facultyRepository.findById(id)
                .map(facultyMapper::toFacultyResponse)
                .orElseThrow(() -> new AppException(ErrorCode.FACULTY_NOT_FOUND));
    }


    @Override
    public FacultyResponse updateFaculty(String id, FacultyUpdateRequest request) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.FACULTY_NOT_FOUND));
        facultyMapper.updateFacultyFromRequest(faculty, request);

        return facultyMapper.toFacultyResponse(facultyRepository.save(faculty));
    }

    @Override
    public void deleteFaculty(String id) {
        if (!facultyRepository.existsById(id)) {
            throw new AppException(ErrorCode.FACULTY_NOT_FOUND);
        }
        facultyRepository.deleteById(id);
    }
}


