package com.marius.sms.backend.entities;

import java.time.LocalDateTime;

public class Curricula {
    private Integer curriculaId;
    private Program program;
    private String academicYear; //2024-2025 sample
    private Integer version;
    private boolean isActive;
    private LocalDateTime curriculaCreatedAt;

    public Curricula() {}

    public Curricula(Integer curriculaId, Program program, String academicYear, Integer version, boolean isActive, LocalDateTime curriculaCreatedAt) {
        this.curriculaId = curriculaId;
        this.program = program;
        this.academicYear = academicYear;
        this.version = version;
        this.isActive = isActive;
        this.curriculaCreatedAt = curriculaCreatedAt;
    }

    public Integer getCurriculaId() {
        return curriculaId;
    }

    public void setCurriculaId(Integer curriculaId) {
        this.curriculaId = curriculaId;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCurriculaCreatedAt() {
        return curriculaCreatedAt;
    }

    public void setCurriculaCreatedAt(LocalDateTime curriculaCreatedAt) {
        this.curriculaCreatedAt = curriculaCreatedAt;
    }

    @Override
    public String toString() {
        return "Curricula{" +
                "curriculaId=" + curriculaId +
                ", program=" + program +
                ", academicYear='" + academicYear + '\'' +
                ", version=" + version +
                ", isActive=" + isActive +
                ", curriculaCreatedAt=" + curriculaCreatedAt +
                '}';
    }
}
