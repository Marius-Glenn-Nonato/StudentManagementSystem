package com.marius.sms.backend.entities;

import java.time.LocalTime;

public class Schedule {
    private int schedule_id;
    private Course course;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;
}
