package com.marius.sms.frontend.cli.views.student;

import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.entities.Course;
import com.marius.sms.backend.entities.Enrollment;
import com.marius.sms.backend.entities.Student;

import java.util.List;

public class StudentGradesView {
    private final Student student;
    private final StudentDAO studentDAO;

    public StudentGradesView(Student student) {
        this.student = student;
        this.studentDAO = new StudentDAO();
    }

    public void show() {
        List<Enrollment> studentCourses = studentDAO.getCoursesOfStudents(student.getStudent_id());

        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║              STUDENT GRADES                 ║");
        System.out.println("╚═════════════════════════════════════════════╝");
        // Print table header
        System.out.printf("%-10s | %-30s | %-7s | %-5s%n", "Course ID", "Course Name", "Credits", "Grade");
        System.out.println("---------------------------------------------------------------");

        // Print each course
        for (Enrollment studentCourse : studentCourses) {
            System.out.printf("%-10s | %-30s | %-7d | %-5s%n",
                    studentCourse.getCourse().getCourse_id(),
                    studentCourse.getCourse().getCourse_name(),
                    studentCourse.getCourse().getCredits(),
                    studentCourse.getGrade() != null ? studentCourse.getGrade() : "N/A");
        }
    }

}
