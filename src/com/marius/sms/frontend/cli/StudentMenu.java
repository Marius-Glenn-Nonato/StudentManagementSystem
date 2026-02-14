package com.marius.sms.frontend.cli;

import com.marius.sms.backend.entities.Student;
import java.util.Scanner;

public class StudentMenu {
    private final Student student;
    private final Scanner scanner;

    public StudentMenu(Student student) {
        this.student = student;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display the student menu
     */
    public void showMenu() {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║         STUDENT MENU                      ║");
        System.out.println("║  Welcome, " + String.format("%-31s", student.getFirst_name() + " " + student.getLast_name()) + "║");
        System.out.println("║  Student #: " + String.format("%-31s", student.getStudent_number()) + "║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println();

        boolean running = true;
        while (running) {
            System.out.println("1. View My Courses");
            System.out.println("2. View My Grades");
            System.out.println("3. View My Profile");
            System.out.println("4. Edit Profile");
            System.out.println("5. Settings");
            System.out.println("6. Logout");
            System.out.println();
            System.out.print("Select an option: ");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    System.out.println("View My Courses - Not yet implemented");
                    break;
                case "2":
                    System.out.println("View My Grades - Not yet implemented");
                    break;
                case "3":
                    viewProfile();
                    break;
                case "4":
                    System.out.println("Edit Profile - Not yet implemented");
                    break;
                case "5":
                    System.out.println("Settings - Not yet implemented");
                    break;
                case "6":
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("✗ Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }

    /**
     * View student profile information
     */
    private void viewProfile() {
        System.out.println();
        System.out.println("═══ MY PROFILE ═══");
        System.out.println("Name: " + student.getFirst_name() + " " + student.getLast_name());
        System.out.println("Student Number: " + student.getStudent_number());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Date of Birth: " + student.getDate_of_birth());
        System.out.println("Username: " + student.getUsername());
        System.out.println("Member Since: " + student.getCreated_at());
        System.out.println();
    }
}
