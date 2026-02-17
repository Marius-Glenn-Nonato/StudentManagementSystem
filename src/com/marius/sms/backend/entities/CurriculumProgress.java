package com.marius.sms.backend.entities;

import java.time.LocalDateTime;

public class CurriculumProgress {
    private Integer curriculum_progress_id;
    private Student student;
    private CurriculumCourse curriculum_course;
    private String status; //NOT_TAKEN, IN_PROGRESS, COMPLETED, FAILED
    private Double finalGrade;
    private LocalDateTime completedAt;

    public CurriculumProgress() {}

    public CurriculumProgress(Integer curriculum_progress_id, Student student, CurriculumCourse curriculum_course, String status, Double finalGrade, LocalDateTime completedAt) {
        this.curriculum_progress_id = curriculum_progress_id;
        this.student = student;
        this.curriculum_course = curriculum_course;
        this.status = status;
        this.finalGrade = finalGrade;
        this.completedAt = completedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CurriculumCourse getCurriculum_course() {
        return curriculum_course;
    }

    public void setCurriculum_course(CurriculumCourse curriculum_course) {
        this.curriculum_course = curriculum_course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getCurriculum_progress_id() {
        return curriculum_progress_id;
    }

    public void setCurriculum_progress_id(Integer curriculum_progress_id) {
        this.curriculum_progress_id = curriculum_progress_id;
    }

    @Override
    public String toString() {
        return "CurriculumProgress{" +
                "curriculum_progress_id=" + curriculum_progress_id +
                ", student=" + student +
                ", curriculum_course=" + curriculum_course +
                ", status='" + status + '\'' +
                ", finalGrade=" + finalGrade +
                ", completedAt=" + completedAt +
                '}';
    }
}
