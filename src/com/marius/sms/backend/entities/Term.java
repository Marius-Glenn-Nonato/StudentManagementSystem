package com.marius.sms.backend.entities;

import java.time.LocalDate;

public class Term {
    private Integer term_id;
    private String term_name; // 2026-1,
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive; //signifying one term should be active

    public Term(){};

    public Term(LocalDate endDate, Integer term_id, String term_name, LocalDate startDate, boolean isActive) {
        this.endDate = endDate;
        this.term_id = term_id;
        this.term_name = term_name;
        this.startDate = startDate;
        this.isActive = isActive;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getTerm_id() {
        return term_id;
    }

    public void setTerm_id(Integer term_id) {
        this.term_id = term_id;
    }

    public String getTerm_name() {
        return term_name;
    }

    public void setTerm_name(String term_name) {
        this.term_name = term_name;
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
                "term_id=" + term_id +
                ", term_name='" + term_name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                '}';
    }
}
