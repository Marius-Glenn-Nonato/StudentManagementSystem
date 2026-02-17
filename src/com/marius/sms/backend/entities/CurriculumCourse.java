package com.marius.sms.backend.entities;

public class CurriculumCourse {
    private Integer curriculumCourseId;
    private Curricula curricula;//Can extend Curricula object but leave it as is for now
    private Course course;
    private Integer yearLevel;
    private Integer semester; // both yuearLevel and semester defines the intended year and semester the course is supposed to be taken
}
