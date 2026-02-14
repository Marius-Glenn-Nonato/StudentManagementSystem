package com.marius.sms.frontend.cli.views.student;

import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.entities.Student;

public class StudentProfileView {
    private final Student student;
    private final StudentDAO studentDAO;

    public StudentProfileView(Student student) {
        this.student = student;
        this.studentDAO = new StudentDAO();
    }
    //You already have complete student info: s.student_id, s.first_name, s.last_name,s.date_of_birth,s.user_id, u.username, u.email, u.password_hash, u.role_id, u.created_at
    public void show() {
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║              STUDENT PROFILE                ║");
        System.out.println("╚═════════════════════════════════════════════╝");

        // Use printf to align columns
        System.out.printf("%-15s : %s%n", "Student ID", student.getStudent_id());
        System.out.printf("%-15s : %s %s%n", "Name", student.getFirst_name(), student.getLast_name());
        System.out.printf("%-15s : %s%n", "Date of Birth", student.getDate_of_birth());
        System.out.printf("%-15s : %s%n", "Username", student.getUsername());
        System.out.printf("%-15s : %s%n", "Email", student.getEmail());
        System.out.printf("%-15s : %s%n", "Created At", student.getCreated_at());

        System.out.println("──────────────────────────────────────────────");
    }

}
