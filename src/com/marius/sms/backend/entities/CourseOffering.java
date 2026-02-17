package com.marius.sms.backend.entities;

import java.time.LocalDate;

public class CourseOffering {
    private Integer course_offering_id;
    private Course course;
    private String sectionCode;
    private Teacher teacher;
    private Term term;
    private Integer capacity;
    private LocalDate courseOfferingCreatedAt;

    public CourseOffering() {}

    public CourseOffering(LocalDate courseOfferingCreatedAt, Integer capacity, Term term, Teacher teacher, String sectionCode, Course course, Integer course_offering_id) {
        this.courseOfferingCreatedAt = courseOfferingCreatedAt;
        this.capacity = capacity;
        this.term = term;
        this.teacher = teacher;
        this.sectionCode = sectionCode;
        this.course = course;
        this.course_offering_id = course_offering_id;
    }

    public LocalDate getCourseOfferingCreatedAt() {
        return courseOfferingCreatedAt;
    }

    public void setCourseOfferingCreatedAt(LocalDate courseOfferingCreatedAt) {
        this.courseOfferingCreatedAt = courseOfferingCreatedAt;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getCourse_offering_id() {
        return course_offering_id;
    }

    public void setCourse_offering_id(Integer course_offering_id) {
        this.course_offering_id = course_offering_id;
    }

    @Override
    public String toString() {
        return "CourseOffering{" +
                "course_offering_id=" + course_offering_id +
                ", course=" + course +
                ", sectionCode='" + sectionCode + '\'' +
                ", teacher=" + teacher +
                ", term=" + term +
                ", capacity=" + capacity +
                ", courseOfferingCreatedAt=" + courseOfferingCreatedAt +
                '}';
    }
}
