package com.duynghia.Academic.Management.System.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {

    // =========================================================================
    // SERIES 1000: LỖI HỆ THỐNG & XÁC THỰC (SYSTEM & AUTHENTICATION)
    // =========================================================================
    UNCATEGORIZED_EXCEPTION(1000, "Lỗi hệ thống không xác định", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Mã lỗi không hợp lệ", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1002, "Chưa xác thực danh tính", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1003, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),

    // =========================================================================
    // SERIES 2000: TÀI KHOẢN & VALIDATE CƠ BẢN (USER IDENTITY)
    // =========================================================================
    USER_NOT_FOUND(2001, "Không tìm thấy thông tin hồ sơ người dùng", HttpStatus.NOT_FOUND),
    USER_EXISTED(2002, "Tên đăng nhập đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(2005, "Người dùng không tồn tại", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(2003, "Vai trò không tồn tại", HttpStatus.NOT_FOUND),
    ROLE_REQUIRED(2004, "Vai trò không được để trống", HttpStatus.BAD_REQUEST),

    USERNAME_REQUIRED(2010, "Tên đăng nhập không được để trống", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(2011, "Tên đăng nhập phải nằm trong khoảng {min} đến {max} kí tự", HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED(2012, "Mật khẩu không được để trống", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(2013, "Mật khẩu phải nằm trong khoảng {min} đến {max} kí tự", HttpStatus.BAD_REQUEST),

    FULLNAME_REQUIRED(2020, "Họ và tên không được để trống", HttpStatus.BAD_REQUEST),
    FULL_NAME_INVALID_LENGTH(2021, "Họ tên không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    ENGLISH_NAME_INVALID_LENGTH(2022, "Tên tiếng Anh không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),

    EMAIL_REQUIRED(2030, "Email không được để trống", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(2031, "Email không đúng định dạng", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID_LENGTH(2032, "Email không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    PHONE_INVALID_FORMAT(2033, "Số điện thoại không hợp lệ", HttpStatus.BAD_REQUEST),
    ADDRESS_INVALID_LENGTH(2034, "Địa chỉ không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),

    // =========================================================================
    // SERIES 3000: NGƯỜI DÙNG HỌC THUẬT (STUDENT & LECTURER)
    // =========================================================================
    STUDENT_NOT_FOUND(3001, "Không tìm thấy thông tin hồ sơ sinh viên", HttpStatus.NOT_FOUND),
    STUDENT_ID_REQUIRED(3002, "Mã sinh viên không được để trống", HttpStatus.BAD_REQUEST),
    STUDENT_ID_INVALID_LENGTH(3003, "Mã sinh viên không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),

    LECTURER_NOT_FOUND(3010, "Không tìm thấy thông tin hồ sơ giảng viên", HttpStatus.NOT_FOUND),
    LECTURER_ID_REQUIRED(3011, "Mã giảng viên không được để trống", HttpStatus.BAD_REQUEST),
    LECTURER_ID_INVALID_LENGTH(3012, "Mã giảng viên không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    ACADEMIC_TITLE_INVALID_LENGTH(3013, "Học hàm không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    DEGREE_INVALID_LENGTH(3014, "Học vị không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    POSITION_INVALID_LENGTH(3015, "Chức danh không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),

    // =========================================================================
    // SERIES 4000: CƠ CẤU TỔ CHỨC (FACULTY, DEPARTMENT, CLASS)
    // =========================================================================
    FACULTY_NOT_FOUND(4001, "Không tìm thấy thông tin khoa", HttpStatus.NOT_FOUND),
    FACULTY_REQUIRED(4002, "Khoa quản lý không được để trống", HttpStatus.BAD_REQUEST),
    FACULTY_ID_REQUIRED(4003, "Mã khoa không được để trống", HttpStatus.BAD_REQUEST),
    FACULTY_ID_INVALID_LENGTH(4004, "Mã khoa không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    FACULTY_NAME_REQUIRED(4005, "Tên khoa không được để trống", HttpStatus.BAD_REQUEST),
    FACULTY_NAME_INVALID_LENGTH(4006, "Tên khoa không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    FACULTY_NOT_EXISTED(4007, "Khoa không tồn tại", HttpStatus.BAD_REQUEST),

    DEPARTMENT_NOT_FOUND(4010, "Không tìm thấy thông tin bộ môn", HttpStatus.NOT_FOUND),
    DEPARTMENT_EXISTED(4011, "Mã bộ môn đã tồn tại", HttpStatus.BAD_REQUEST),
    DEPARTMENT_ID_REQUIRED(4012, "Mã bộ môn không được để trống", HttpStatus.BAD_REQUEST),
    DEPARTMENT_ID_INVALID_LENGTH(4013, "Mã bộ môn không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    DEPARTMENT_NAME_REQUIRED(4014, "Tên bộ môn không được để trống", HttpStatus.BAD_REQUEST),
    DEPARTMENT_NAME_INVALID_LENGTH(4015, "Tên bộ môn không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    HEAD_OF_DEPARTMENT_INVALID_LENGTH(4016, "Mã trưởng bộ môn không vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),

    CLASS_NOT_FOUND(4020, "Không tìm thấy thông tin lớp học", HttpStatus.NOT_FOUND),
    CLASS_ID_REQUIRED(4021, "Mã lớp không được để trống", HttpStatus.BAD_REQUEST),
    CLASS_ID_INVALID_LENGTH(4022, "Mã lớp không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    CLASS_NAME_REQUIRED(4023, "Tên lớp không được để trống", HttpStatus.BAD_REQUEST),
    CLASS_NAME_INVALID_LENGTH(4024, "Tên lớp không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),
    COURSE_YEAR_REQUIRED(4025, "Khóa học không được để trống", HttpStatus.BAD_REQUEST),
    COURSE_YEAR_INVALID_LENGTH(4026, "Khóa học không được vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),

    // =========================================================================
    // SERIES 5000: ĐÀO TẠO CỐT LÕI (PROGRAM & COURSE)
    // =========================================================================
    PROGRAM_NOT_FOUND(5001, "Không tìm thấy thông tin chương trình đào tạo", HttpStatus.NOT_FOUND),
    PROGRAM_EXISTED(5002, "Mã chương trình đào tạo đã tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),
    PROGRAM_REQUIRED(5003, "Chương trình đào tạo không được để trống", HttpStatus.BAD_REQUEST),
    PROGRAM_ID_INVALID_LENGTH(5004, "Mã chương trình đào tạo không vượt quá {max} ký tự", HttpStatus.BAD_REQUEST),

    COURSE_NOT_FOUND(5010, "Không tìm thấy thông tin học phần", HttpStatus.NOT_FOUND),
    COURSE_EXISTED(5011, "Mã học phần đã tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),
    COURSE_ID_REQUIRED(5012, "Mã học phần không được để trống", HttpStatus.BAD_REQUEST),
    CREDITS_REQUIRED(5013, "Số tín chỉ không được để trống", HttpStatus.BAD_REQUEST),

    COURSE_ALREADY_IN_PROGRAM(5020, "Học phần này đã được thêm vào chương trình đào tạo từ trước", HttpStatus.BAD_REQUEST),
    COURSE_NOT_EXISTED_IN_PROGRAM(5021, "Học phần này không tồn tại trong chương trình đào tạo", HttpStatus.BAD_REQUEST),

    // =========================================================================
    // SERIES 6000: NGHIỆP VỤ LỚP HỌC PHẦN (COURSE SECTION & REGISTRATION)
    // =========================================================================
    SECTION_NOT_FOUND(6001, "Không tìm thấy thông tin lớp học phần", HttpStatus.NOT_FOUND),
    SECTION_EXISTED(6002, "Mã lớp học phần đã được mở trong hệ thống", HttpStatus.BAD_REQUEST),
    SECTION_ID_INVALID_LENGTH(6003, "Mã lớp học phần không được vượt quá {max} kí tự", HttpStatus.BAD_REQUEST),
    SECTION_NAME_REQUIRED(6004, "Tên lớp học phần không được để trống", HttpStatus.BAD_REQUEST),

    SEMESTER_REQUIRED(6010, "Kỳ học không được bỏ trống", HttpStatus.BAD_REQUEST),
    INVALID_SEMESTER(6011, "Học kỳ đăng ký không hợp lệ", HttpStatus.BAD_REQUEST),

    STATUS_REQUIRED(6020, "Trạng thái của lớp học phần là bắt buộc", HttpStatus.BAD_REQUEST),
    CANNOT_UPDATE_CANCELLED_SECTION(6021, "Không thể chỉnh sửa lớp học phần đã bị hủy", HttpStatus.BAD_REQUEST),
    CANNOT_UPDATE_COMPLETED_SECTION(6022, "Không thể thay đổi trạng thái của lớp học phần đã kết thúc", HttpStatus.BAD_REQUEST),
    INVALID_STATUS_TRANSITION(6023, "Luồng chuyển đổi trạng thái không hợp lệ đối với lớp đang diễn ra", HttpStatus.BAD_REQUEST),

    MAX_CAPACITY_INVALID(6030, "Số lượng sinh viên tối đa không hợp lệ", HttpStatus.BAD_REQUEST),
    SECTION_IS_FULL(6031, "Lớp học phần đã đạt số lượng đăng ký tối đa", HttpStatus.BAD_REQUEST),
    STUDENT_ALREADY_IN_SECTION(6032, "Sinh viên đã đăng ký lớp học phần này rồi", HttpStatus.BAD_REQUEST),
    PREREQUISITE_NOT_MET(6033, "Sinh viên chưa tích lũy đủ học phần tiên quyết để đăng ký môn này", HttpStatus.BAD_REQUEST),
    NO_ACTIVE_REGISTRATION_PERIOD(6034, "Không có đợt đăng kí nào dành cho bạn", HttpStatus.BAD_REQUEST);


    int code;
    String message;
    HttpStatusCode httpStatusCode;
}