package com.marius.sms.frontend.cli.views.teacher;

import com.marius.sms.backend.dao.TeacherDAO;
import com.marius.sms.backend.entities.Course;
import com.marius.sms.backend.entities.Teacher;
import com.marius.sms.backend.service.TeacherService;

import java.util.List;

public class TeacherCoursesView {
    private Teacher teacher;
    private final TeacherDAO teacherDAO;
    private final TeacherService teacherService = new TeacherService();
    public TeacherCoursesView(Teacher teacher) {
        this.teacher = teacher;
        this.teacherDAO = new TeacherDAO();
    }
    public void show() {
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║         TEACHER COURSES                       ║");
        System.out.println("║  Courses of " + String.format("%-31s", teacher.getFirst_name() + "║"));
        System.out.println("╚═════════════════════════════════════════════╝");
        System.out.println();
        showTeacherCourses(teacher);
    }

    private void showTeacherCourses(Teacher teacher) {
        List<Course> listOfCourses = teacherService.getTeacherCourses(teacher);
    }
}
