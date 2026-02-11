package com.marius.sms.backend.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Enrollment {
    private Integer enrollment_id;
    private Integer student_id;
    private Integer course_id;
    private BigDecimal grade;
    private Timestamp enrolled_at;

    public Enrollment() { }

    public Enrollment(Integer enrollment_id, Integer student_id, Integer course_id, BigDecimal grade, Timestamp enrolled_at) {
        this.enrollment_id = enrollment_id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.grade = grade;
        this.enrolled_at = enrolled_at;
    }

    public Integer getEnrollment_id() { return enrollment_id; }
    public void setEnrollment_id(Integer enrollment_id) { this.enrollment_id = enrollment_id; }

    public Integer getStudent_id() { return student_id; }
    public void setStudent_id(Integer student_id) { this.student_id = student_id; }

    public Integer getCourse_id() { return course_id; }
    public void setCourse_id(Integer course_id) { this.course_id = course_id; }

    public BigDecimal getGrade() { return grade; }
    public void setGrade(BigDecimal grade) { this.grade = grade; }

    public Timestamp getEnrolled_at() { return enrolled_at; }
    public void setEnrolled_at(Timestamp enrolled_at) { this.enrolled_at = enrolled_at; }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollment_id=" + enrollment_id +
                ", student_id=" + student_id +
                ", course_id=" + course_id +
                ", grade=" + grade +
                ", enrolled_at=" + enrolled_at +
                '}';
    }
}
