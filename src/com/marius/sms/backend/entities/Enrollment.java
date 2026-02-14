package com.marius.sms.backend.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Enrollment {
    private Integer enrollment_id;
    private Course course;
    private Student student;
    // private Integer student_id;
    // private String course_id;
    private Double grade;
    private LocalDateTime enrolled_at;

    public Enrollment() { }

    public Enrollment(Integer enrollment_id, Student student, Course coures, Double grade, LocalDateTime enrolled_at) {
        this.enrollment_id = enrollment_id;
        this.student = student;
        this.course = coures;
        this.grade = grade;
        this.enrolled_at = enrolled_at;
    }

    public Integer getEnrollment_id() { return enrollment_id; }
    public void setEnrollment_id(Integer enrollment_id) { this.enrollment_id = enrollment_id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Double getGrade() { return grade; }
    public void setGrade(Double grade) { this.grade = grade; }

    public LocalDateTime getEnrolled_at() { return enrolled_at; }
    public void setEnrolled_at(LocalDateTime enrolled_at) { this.enrolled_at = enrolled_at; }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollment_id=" + enrollment_id +
                ", student_id=" + student +
                ", course_id=" + course +
                ", grade=" + grade +
                ", enrolled_at=" + enrolled_at +
                '}';
    }
}
