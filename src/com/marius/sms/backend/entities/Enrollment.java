package com.marius.sms.backend.entities;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Enrollment {
    private Integer enrollmentId;
    private Student student;
    private CourseOffering course_offering;
    private String status; //ACTIVE, DROPPED, COMPLETED
    private Double prelimGrade;
    private Double midtermGrade;
    private Double tentativeFinalGrade;
    private Double finalGrade;
    private Integer intendedYearLevel; //both intendedYearLevel and intendedSemester Purpose: tells the system what year/semester the student is “taking” this course in Especially important for irregular students, because they may enroll out-of-sequence
    private Integer intendedSemester;
    private LocalDateTime enrolled_at;
    private LocalDateTime completed_at;

    public Enrollment() { }

    public Enrollment(Integer enrollmentId, Student student, String status, CourseOffering course_offering, Double prelimGrade, Double midtermGrade, Double tentativeFinalGrade, Double finalGrade, Integer intendedYearLevel, Integer intendedSemester, LocalDateTime enrolled_at, LocalDateTime completed_at) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.status = status;
        this.course_offering = course_offering;
        this.prelimGrade = prelimGrade;
        this.midtermGrade = midtermGrade;
        this.tentativeFinalGrade = tentativeFinalGrade;
        this.finalGrade = finalGrade;
        this.intendedYearLevel = intendedYearLevel;
        this.intendedSemester = intendedSemester;
        this.enrolled_at = enrolled_at;
        this.completed_at = completed_at;
    }


    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseOffering getCourse_offering() {
        return course_offering;
    }

    public void setCourse_offering(CourseOffering course_offering) {
        this.course_offering = course_offering;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrelimGrade() {
        return prelimGrade;
    }

    public void setPrelimGrade(Double prelimGrade) {
        this.prelimGrade = prelimGrade;
    }

    public Double getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(Double midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public Double getTentativeFinalGrade() {
        return tentativeFinalGrade;
    }

    public void setTentativeFinalGrade(Double tentativeFinalGrade) {
        this.tentativeFinalGrade = tentativeFinalGrade;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Integer getIntendedYearLevel() {
        return intendedYearLevel;
    }

    public void setIntendedYearLevel(Integer intendedYearLevel) {
        this.intendedYearLevel = intendedYearLevel;
    }

    public Integer getIntendedSemester() {
        return intendedSemester;
    }

    public void setIntendedSemester(Integer intendedSemester) {
        this.intendedSemester = intendedSemester;
    }

    public LocalDateTime getEnrolled_at() {
        return enrolled_at;
    }

    public void setEnrolled_at(LocalDateTime enrolled_at) {
        this.enrolled_at = enrolled_at;
    }

    public LocalDateTime getCompleted_at() {
        return completed_at;
    }

    public void setCompleted_at(LocalDateTime completed_at) {
        this.completed_at = completed_at;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", student=" + student +
                ", course_offering=" + course_offering +
                ", status='" + status + '\'' +
                ", prelimGrade=" + prelimGrade +
                ", midtermGrade=" + midtermGrade +
                ", tentativeFinalGrade=" + tentativeFinalGrade +
                ", finalGrade=" + finalGrade +
                ", intendedYearLevel=" + intendedYearLevel +
                ", intendedSemester=" + intendedSemester +
                ", enrolled_at=" + enrolled_at +
                ", completed_at=" + completed_at +
                '}';
    }
}
