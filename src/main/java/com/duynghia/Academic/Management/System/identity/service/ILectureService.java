package com.duynghia.Academic.Management.System.identity.service;

import com.duynghia.Academic.Management.System.identity.dto.request.LectureCreationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.LectureResponse;
import jakarta.validation.Valid;

public interface ILectureService {
    public LectureResponse createLecture(@Valid LectureCreationRequest request);
}
