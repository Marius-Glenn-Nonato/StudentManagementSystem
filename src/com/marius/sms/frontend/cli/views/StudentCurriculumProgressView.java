package com.marius.sms.frontend.cli.views;

import com.marius.sms.backend.dao.CurriculumProgressDAO;
import com.marius.sms.backend.entities.Course;
import com.marius.sms.backend.entities.CurriculumCourse;
import com.marius.sms.backend.entities.CurriculumProgress;
import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.service.StudentService;

import java.util.List;

public class StudentCurriculumProgressView {
    private final Student student;
    private StudentService studentService;

    public StudentCurriculumProgressView(Student student) {
        this.student = student;
        studentService = new StudentService();
    }
    public void show() {

        List<CurriculumProgress> list =
                studentService.getCurriculumProgressOfStudent(student.getStudent_id());

        if (list.isEmpty()) {
            System.out.println("No curriculum records found.");
            return;
        }

        // Print Header
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║           CURRICULUM PROGRESS CHECKLIST FOR STUDENT                           ║");
        System.out.println("║" + String.format(" Student ID: %-58s ║", student.getStudent_id()));
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");

        int currentYear = -1;
        int currentSemester = -1;
        int totalCourses = 0;
        int completedCourses = 0;

        for (CurriculumProgress cp : list) {

            CurriculumCourse cc = cp.getCurriculum_course();
            Course c = cc.getCourse();

            int year = cc.getYearLevel();
            int sem = cc.getSemester();

            totalCourses++;
            if ("COMPLETED".equalsIgnoreCase(String.valueOf(cp.getStatus()))) {
                completedCourses++;
            }

            // Year Header
            if (year != currentYear) {
                currentYear = year;
                currentSemester = -1;

                System.out.println("\n┌─── YEAR LEVEL " + currentYear + " ──────────────────────────────────────────────────────┐");
            }

            // Semester Header
            if (sem != currentSemester) {
                currentSemester = sem;

                System.out.println("│");
                System.out.println("│  📚 Semester " + currentSemester);
                System.out.println("│  ─────────────────────────────────────────────────────────────────────────────");
                System.out.printf("│  %-10s %-35s %-7s %-20s %-8s%n",
                        "Code", "Course Name", "Units", "Status", "Grade");
                System.out.println("│  ─────────────────────────────────────────────────────────────────────────────");
            }

            // Handle NULL status
            String status = cp.getStatus() != null ? cp.getStatus() : "NOT_TAKEN";

            // Handle NULL grade
            String grade = cp.getFinalGrade() != null
                    ? String.format("%.2f", cp.getFinalGrade())
                    : "-";

            // Status indicator
            String statusIndicator = getStatusIndicator(status);

            // Print Row
            System.out.printf("│  %-10s %-35s %-7d %-20s %-8s%n",
                    c.getCourseCode(),
                    truncateString(c.getCourseName(), 33),
                    c.getCourseUnits(),
                    statusIndicator,
                    grade);
        }

        System.out.println("│");
        System.out.println("└──────────────────────────────────────────────────────────────────────────────────┘");

        // Print Summary
        System.out.println("\n┌─── PROGRESS SUMMARY ─────────────────────────────────────────────────────────────┐");
        System.out.printf("│  Total Courses:      %-60d │%n", totalCourses);
        System.out.printf("│  Completed:          %-60d │%n", completedCourses);
        System.out.printf("│  Remaining:          %-60d │%n", (totalCourses - completedCourses));
        int progressPercentage = (totalCourses > 0) ? (completedCourses * 100) / totalCourses : 0;
        System.out.printf("│  Overall Progress:   %-60s │%n", progressPercentage + "%");
        System.out.println("└──────────────────────────────────────────────────────────────────────────────────┘\n");
    }

    /**
     * Returns a formatted status string with visual indicator
     */
    private String getStatusIndicator(String status) {
        if (status == null) {
            return "NOT_TAKEN";
        }
        return switch (status.toUpperCase()) {
            case "COMPLETED" -> "✓ COMPLETED";
            case "ENROLLED" -> "→ ENROLLED";
            case "PASSED" -> "✓ PASSED";
            case "FAILED" -> "✗ FAILED";
            case "IN_PROGRESS" -> "⟳ IN_PROGRESS";
            default -> status;
        };
    }

    /**
     * Truncates a string to a specified length with ellipsis
     */
    private String truncateString(String str, int maxLength) {
        if (str == null) {
            return "";
        }
        if (str.length() > maxLength) {
            return str.substring(0, maxLength - 2) + "..";
        }
        return str;
    }

}
