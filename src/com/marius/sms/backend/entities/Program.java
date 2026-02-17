package com.marius.sms.backend.entities;

import java.time.LocalDate;

public class Program {
    private Integer programId;
    private String programName;
    private LocalDate programCreatedAt;

    public Program(){};
    public Program(Integer programId, String programName, LocalDate programCreatedAt) {
        this.programId = programId;
        this.programName = programName;
        this.programCreatedAt = programCreatedAt;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public LocalDate getProgramCreatedAt() {
        return programCreatedAt;
    }

    public void setProgramCreatedAt(LocalDate programCreatedAt) {
        this.programCreatedAt = programCreatedAt;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId=" + programId +
                ", programName='" + programName + '\'' +
                ", programCreatedAt=" + programCreatedAt +
                '}';
    }
}
