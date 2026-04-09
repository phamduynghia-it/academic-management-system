package com.duynghia.Academic.Management.System.academic.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseSectionStudentId implements Serializable {

    @Column(name = "section_id", length = 25) // MaLopHocPhan
    String sectionId;

    @Column(name = "student_id", length = 10) // MaSinhVien
    String studentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseSectionStudentId that = (CourseSectionStudentId) o;
        return Objects.equals(sectionId, that.sectionId) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionId, studentId);
    }
}
