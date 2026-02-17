package com.marius.sms.backend.entities;

import java.time.LocalTime;

public class OfferingSchedule {
    private Integer offeringScheduleId;
    private CourseOffering courseOffering;
    private String dayOfWeek;// MON, TUE, WED, THU, FRI, SAT, SUN
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;

    public OfferingSchedule(){}

    public OfferingSchedule(Integer offeringScheduleId, CourseOffering courseOffering, String dayOfWeek, LocalTime startTime, LocalTime endTime, String room) {
        this.offeringScheduleId = offeringScheduleId;
        this.courseOffering = courseOffering;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public Integer getOfferingScheduleId() {
        return offeringScheduleId;
    }

    public void setOfferingScheduleId(Integer offeringScheduleId) {
        this.offeringScheduleId = offeringScheduleId;
    }

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public void setCourseOffering(CourseOffering courseOffering) {
        this.courseOffering = courseOffering;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "OfferingSchedule{" +
                "offeringScheduleId=" + offeringScheduleId +
                ", courseOffering=" + courseOffering +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", room='" + room + '\'' +
                '}';
    }
}
