package com.duynghia.Academic.Management.System.academic.enums;

public enum CourseSectionStatus {
    PLANNED,      // Đã lên kế hoạch (Chờ đến đợt mở đăng ký)
    ONGOING,      // Đang diễn ra (Giảng viên đang dạy, sinh viên đang học)
    COMPLETED,    // Đã kết thúc (Đã thi, đã chốt điểm)
    CANCELLED     // Bị hủy (Mở ra nhưng chỉ có 2-3 người đăng ký nên trường hủy lớp)
}
