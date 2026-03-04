package com.marius.sms.backend.entities;

public class CurriculumCourse {
    private Integer curriculumCourseId;
    private Curricula curricula;//Can extend Curricula object but leave it as is for now
    private Course course;
    private Integer yearLevel;
    private Integer semester; // both yuearLevel and semester defines the intended year and semester the course is supposed to be taken

    public CurriculumCourse() {}
    public CurriculumCourse(Integer curriculumCourseId, Curricula curricula, Course course, Integer yearLevel, Integer semester) {
        this.curriculumCourseId = curriculumCourseId;
        this.curricula = curricula;
        this.course = course;
        this.yearLevel = yearLevel;
        this.semester = semester;
    }

    public Integer getCurriculumCourseId() {
        return curriculumCourseId;
    }

    public void setCurriculumCourseId(Integer curriculumCourseId) {
        this.curriculumCourseId = curriculumCourseId;
    }

    public Curricula getCurricula() {
        return curricula;
    }

    public void setCurricula(Curricula curricula) {
        this.curricula = curricula;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(Integer yearLevel) {
        this.yearLevel = yearLevel;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "CurriculumCourse{" +
                "curriculumCourseId=" + curriculumCourseId +
                ", curricula=" + curricula +
                ", course=" + course +
                ", yearLevel=" + yearLevel +
                ", semester=" + semester +
                '}';
    }
}
