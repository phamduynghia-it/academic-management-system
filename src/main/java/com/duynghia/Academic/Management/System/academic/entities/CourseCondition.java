package com.duynghia.Academic.Management.System.academic.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "course_condition")
public class CourseCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "required_course_id")
    private Course requiredCourse;
}

