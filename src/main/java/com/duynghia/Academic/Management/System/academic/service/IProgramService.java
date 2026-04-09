package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCreationRequest;
import com.duynghia.Academic.Management.System.academic.entities.Program;

public interface IProgramService {
    public Program createProgram(ProgramCreationRequest request);
}
