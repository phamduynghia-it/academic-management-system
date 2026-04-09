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
public class ProgramCourseId implements Serializable {

    @Column(name = "program_id", length = 25)
    String programId;

    @Column(name = "course_id", length = 25)
    String courseId;

    // Bắt buộc phải override equals và hashCode cho Composite Key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramCourseId that = (ProgramCourseId) o;
        return Objects.equals(programId, that.programId) &&
                Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId, courseId);
    }
}