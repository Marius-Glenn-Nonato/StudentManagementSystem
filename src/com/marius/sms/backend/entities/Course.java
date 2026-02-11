package com.marius.sms.backend.entity;

import java.sql.Timestamp;

public class Course {
    private Integer course_id;
    private String course_code;
    private String course_name;
    private Integer credits;
    private Timestamp created_at;

    public Course() { }

    public Course(Integer course_id, String course_code, String course_name, Integer credits, Timestamp created_at) {
        this.course_id = course_id;
        this.course_code = course_code;
        this.course_name = course_name;
        this.credits = credits;
        this.created_at = created_at;
    }

    public Integer getCourse_id() { return course_id; }
    public void setCourse_id(Integer course_id) { this.course_id = course_id; }

    public String getCourse_code() { return course_code; }
    public void setCourse_code(String course_code) { this.course_code = course_code; }

    public String getCourse_name() { return course_name; }
    public void setCourse_name(String course_name) { this.course_name = course_name; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public Timestamp getCreated_at() { return created_at; }
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", course_code='" + course_code + '\'' +
                ", course_name='" + course_name + '\'' +
                ", credits=" + credits +
                ", created_at=" + created_at +
                '}';
    }
}
