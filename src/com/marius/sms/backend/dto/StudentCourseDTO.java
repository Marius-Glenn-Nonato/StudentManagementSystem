package com.marius.sms.backend.dto;

import com.marius.sms.backend.entities.Enrollment;
import com.marius.sms.backend.entities.OfferingSchedule;

import java.util.List;

public class StudentCourseDTO {
    private Enrollment enrollment;                // full Enrollment object
    private List<OfferingSchedule> offeringSchedules;

    public StudentCourseDTO(){};

    public StudentCourseDTO(Enrollment enrollment, List<OfferingSchedule> offeringSchedules) {
        this.enrollment = enrollment;
        this.offeringSchedules = offeringSchedules;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public List<OfferingSchedule> getOfferingSchedules() {
        return offeringSchedules;
    }

    public void setOfferingSchedules(List<OfferingSchedule> offeringSchedules) {
        this.offeringSchedules = offeringSchedules;
    }
}

