package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.CourseCreationRequest;
import com.duynghia.Academic.Management.System.academic.service.ICourseService;
import com.duynghia.Academic.Management.System.common.BaseExcelImporter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseExcelService extends BaseExcelImporter {
    ICourseService courseService;

    @Override
    protected void processRow(Row row, Object... args) throws Exception {

        String courseId = getCellValue(row.getCell(0));
        String courseName = getCellValue(row.getCell(1));
        String englishName = getCellValue(row.getCell(2));
        String departmentId = getCellValue(row.getCell(3));
        String creditsValue = getCellValue(row.getCell(4));
        Integer credits = creditsValue.isBlank()
                ? null
                : Integer.valueOf(creditsValue);
        Float processWeight = Float.valueOf(getCellValue(row.getCell(5)));
        Float finalWeight = Float.valueOf(getCellValue(row.getCell(6)));
        CourseCreationRequest courseReq = CourseCreationRequest.builder()
                .courseId(courseId)
                .courseName(courseName)
                .englishName(englishName)
                .departmentId(departmentId)
                .credits(credits)
                .processWeight(processWeight)
                .finalWeight(finalWeight)
                .build();

        courseService.createCourse(courseReq);
    }

    @Override
    protected String getRowIdentifier(Row row) {
        return getCellValue(row.getCell(0));
    }
}
