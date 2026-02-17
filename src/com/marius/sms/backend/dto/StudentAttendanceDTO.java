package com.marius.sms.backend.dto;

import com.marius.sms.backend.entities.Attendance;
import com.marius.sms.backend.entities.CourseOffering;

import java.util.List;

public class StudentAttendanceDTO {
    private CourseOffering courseOffering;
    private Attendance attendance;

    public StudentAttendanceDTO() {}

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public void setCourseOffering(CourseOffering courseOffering) {
        this.courseOffering = courseOffering;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "StudentAttendanceDTO{" +
                "courseOffering=" + courseOffering +
                ", attendance=" + attendance +
                '}';
    }

}
