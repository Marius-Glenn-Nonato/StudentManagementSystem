package com.marius.sms.frontend.cli;

import com.marius.sms.backend.entities.Student;

import java.sql.SQLOutput;
import java.util.Scanner;

public class StudentMenu {
    private final Student student;
    private final Scanner scanner;
    public StudentMenu(Student student) {
        this.student = student;
        this.scanner = new Scanner(System.in);
    }
    public void showMenu() {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║         STUDENT MENU                      ║");
        System.out.println("║  Welcome, " + String.format("%-31s", student.getFirst_name() + " " + student.getLast_name()) + "║");
        System.out.println("║  Student #: " + String.format("%-31s", student.getStudent_id()) + "║");
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
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();

            }

        }
    }

