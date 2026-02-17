package com.marius.sms.backend.entities;

import java.time.LocalDateTime;

public class Course {
    private Integer courseId;
    private String courseCode;
    private String courseName;
    private Integer courseUnits;
    private LocalDateTime courseCreatedAt;

    public Course() { }

    public Course(Integer courseId, String courseCode, String courseName, Integer courseUnits, LocalDateTime courseCreatedAt) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseUnits = courseUnits;
        this.courseCreatedAt = courseCreatedAt;
    }

    public LocalDateTime getCourseCreatedAt() {
        return courseCreatedAt;
    }

    public void setCourseCreatedAt(LocalDateTime courseCreatedAt) {
        this.courseCreatedAt = courseCreatedAt;
    }

    public Integer getCourseUnits() {
        return courseUnits;
    }

    public void setCourseUnits(Integer courseUnits) {
        this.courseUnits = courseUnits;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseUnits=" + courseUnits +
                ", courseCreatedAt=" + courseCreatedAt +
                '}';
    }
}
