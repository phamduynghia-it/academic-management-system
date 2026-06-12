package com.duynghia.Academic.Management.System.common;

import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BaseExcelImporter {
    public final ImportResponse importData(MultipartFile file) {
        int success = 0;
        int fail = 0;
        List<String> errors = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0 || isRowEmpty(row)) {
                    continue; // Bỏ qua tiêu đề và dòng rỗng
                }

                try {
                    processRow(row);
                    success++;
                } catch (ConstraintViolationException e) {
                    // 1. Bắt riêng lỗi Validation (Thiếu field, sai sđt, sai ngày sinh...)
                    fail++;
                    // Lấy ra cái Key (Ví dụ: COHORT_REQUIRED)
                    String enumKey = e.getConstraintViolations().iterator().next().getMessage();
                    String localizedMessage = enumKey;

                    try {
                        // Tự động tìm trong ErrorCode để lấy câu tiếng Việt
                        localizedMessage = ErrorCode.valueOf(enumKey).getMessage();
                    } catch (IllegalArgumentException ex) {
                        // Nếu không tìm thấy trong ErrorCode thì cứ giữ nguyên Key
                    }

                    errors.add("Lỗi dòng " + (row.getRowNum() + 1) + ": " + localizedMessage);
                
                } catch (AppException e) {
                    // 2. Bắt các lỗi nghiệp vụ (Trùng mã SV, Trùng username...)
                    fail++;
                    errors.add("Lỗi dòng " + (row.getRowNum() + 1) + ": " + e.getErrorCode().getMessage());
                } catch (Exception e) {
                    // 3. Bắt các lỗi vặt khác
                    fail++;
                    errors.add("Lỗi dòng " + (row.getRowNum() + 1) + ": Hệ thống từ chối lưu dữ liệu");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("File Excel không hợp lệ hoặc bị hỏng!");
        }

        return ImportResponse.builder()
                .successfulCount(success)
                .failedCount(fail)
                .errorMessages(errors)
                .build();
    }


    // 1. Logic đọc 1 dòng và lưu vào DB
    protected abstract void processRow(Row row) throws Exception;

    // 2. Lấy ra mã nhận diện (Ví dụ cột Mã SV hoặc Mã GV) để in ra thông báo lỗi cho đẹp
    protected abstract String getRowIdentifier(Row row);


    protected String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    private boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    protected LocalDate parseDateOfBirth(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            throw new RuntimeException("Ngày sinh không được để trống");
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                // 1. Nếu Excel nhận diện ô này chuẩn là định dạng Date/Time
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toLocalDate();
                } else {
                    throw new RuntimeException("Ô chứa số nhưng không phải định dạng Ngày tháng chuẩn của Excel");
                }

            case STRING:
                // 2. Nếu Admin nhập dưới dạng Text (Chữ) thông thường (VD: "15/08/2000")
                String dateString = cell.getStringCellValue().trim();
                try {
                    // Cấu hình format mà trường Đại học của bạn quy định (Thường là dd/MM/yyyy)
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    return LocalDate.parse(dateString, formatter);
                } catch (DateTimeParseException e) {
                    // Nếu muốn linh hoạt hơn, bạn có thể try-catch thêm format "yyyy-MM-dd" ở đây
                    throw new RuntimeException("Ngày sinh dạng chữ bị sai định dạng (Yêu cầu: dd/MM/yyyy). Giá trị nhập: " + dateString);
                }

            default:
                throw new RuntimeException("Không thể đọc được dữ liệu ngày sinh (Sai kiểu dữ liệu)");
        }
    }
}