package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.ProgramCourseRequest;
import com.duynghia.Academic.Management.System.academic.service.IProgramService;
import com.duynghia.Academic.Management.System.common.BaseExcelImporter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProgramCourseImportService extends BaseExcelImporter {
    IProgramService programService;

    @Override
    protected void processRow(Row row, Object... args) throws Exception {
        String programId = (String) args[0];
        String courseId = getCellValue(row.getCell(0));
        String semeterVal = getCellValue(row.getCell(1));
        Integer semeter = semeterVal.isBlank()
                ? null
                : Integer.valueOf(semeterVal);
        ProgramCourseRequest request = ProgramCourseRequest.builder()
                .courseId(courseId)
                .semester(semeter)
                .build();
        programService.addCourseToProgram(programId, request);
    }

    @Override
    protected String getRowIdentifier(Row row) {
        return getCellValue(row.getCell(0));
    }
}
