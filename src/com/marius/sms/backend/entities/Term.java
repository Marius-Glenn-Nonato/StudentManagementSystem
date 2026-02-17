package com.marius.sms.backend.entities;

import java.time.LocalDate;

public class Term {
    private Integer termId;
    private String termName; // 2026-1,
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive; //signifying one term should be active

    public Term(){};

    public Term(LocalDate endDate, Integer termId, String termName, LocalDate startDate, boolean isActive) {
        this.endDate = endDate;
        this.termId = termId;
        this.termName = termName;
        this.startDate = startDate;
        this.isActive = isActive;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Term{" +
                "termId=" + termId +
                ", termName='" + termName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                '}';
    }
}
