package com.marius.sms.backend.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Course {
    private Integer course_id;
    private String course_code;
    private String course_name;
    private Integer course_units;
    private LocalDateTime course_created_at;

    public Course() { }

    public Course(Integer course_id, String course_code, String course_name, Integer course_units, LocalDateTime course_created_at) {
        this.course_id = course_id;
        this.course_code = course_code;
        this.course_name = course_name;
        this.course_units = course_units;
        this.course_created_at = course_created_at;
    }

    public LocalDateTime getCourse_created_at() {
        return course_created_at;
    }

    public void setCourse_created_at(LocalDateTime course_created_at) {
        this.course_created_at = course_created_at;
    }

    public Integer getCourse_units() {
        return course_units;
    }

    public void setCourse_units(Integer course_units) {
        this.course_units = course_units;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", course_code='" + course_code + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_units=" + course_units +
                ", course_created_at=" + course_created_at +
                '}';
    }
}
