package com.marius.sms.backend.service;

import com.marius.sms.backend.dao.TeacherDAO;
import com.marius.sms.backend.entities.Course;
import com.marius.sms.backend.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherService {
    private final TeacherDAO teacherDAO = new TeacherDAO();

    public List<Course> getTeacherCourses(Teacher teacher) {
        int teacher_id = teacher.getTeacher_id();
        List<Course> coursesOfTeacher = teacherDAO.getCoursesOfTeacher(teacher_id);
        return coursesOfTeacher;
    }
}
