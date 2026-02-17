package com.marius.sms.frontend.cli.views.teacher;

import com.marius.sms.backend.entities.Course;
import com.marius.sms.backend.entities.Teacher;
import com.marius.sms.backend.service.TeacherService;

import java.util.List;

public class TeacherCoursesView {
    private Teacher teacher;
    private final TeacherService teacherService;

    public TeacherCoursesView(Teacher teacher) {
        this.teacher = teacher;
        teacherService = new TeacherService();
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

        System.out.printf("%-10s %-25s %-10s%n",
                "Course ID", "Course Name", "Credits");
        System.out.println("------------------------------------------------------");

        for (Course course : listOfCourses) {
            System.out.printf("%-10s %-25s %-10d%n", course.getCourseId(), course.getCourseName());
        }
        System.out.println("------------------------------------------------------");
    }

}
