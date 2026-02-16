package com.marius.sms.backend.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Course {
    private String course_id;
    private String course_name;
    private Integer credits;
    private Integer teacher_id;
    private LocalDateTime created_at;

    public Course() { }

    public Course(String course_id,  String course_name, Integer credits, LocalDateTime created_at, Integer teacher_id) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.credits = credits;
        this.created_at = created_at;
        this.teacher_id = teacher_id;
    }

    public String getCourse_id() { return course_id; }
    public void setCourse_id(String course_id) { this.course_id = course_id; }

    public String getCourse_name() { return course_name; }
    public void setCourse_name(String course_name) { this.course_name = course_name; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public LocalDateTime getCreated_at() { return created_at; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }


    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", credits=" + credits +
                ", teacher_id=" + teacher_id +
                ", user_created_at=" + created_at +
                '}';
    }
}
