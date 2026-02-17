package com.marius.sms.backend.entities;

import java.time.LocalDate;

public class Attendance {
    private Integer attendanceId;
    private Enrollment enrollment;
    private LocalDate attendanceDate;
    private String status;

    public Attendance() {}

    public Attendance(Integer attendanceId, Enrollment enrollment, LocalDate attendanceDate, String status) {
        this.attendanceId = attendanceId;
        this.enrollment = enrollment;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", enrollment=" + enrollment +
                ", attendanceDate=" + attendanceDate +
                ", status='" + status + '\'' +
                '}';
    }
}
