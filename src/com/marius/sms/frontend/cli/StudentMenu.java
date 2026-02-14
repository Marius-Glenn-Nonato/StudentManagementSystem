package com.marius.sms.frontend.cli;

import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.exception.InvalidChoiceException;
import com.marius.sms.frontend.cli.router.StudentRouter;

import java.sql.SQLOutput;
import java.util.Scanner;

public class StudentMenu {
    private final Student student;
    private final Scanner scanner;


    //You already have complete student info: s.student_id, s.first_name, s.last_name,s.date_of_birth,s.user_id, u.username, u.email, u.password_hash, u.role_id, u.created_at
    public StudentMenu(Student student) {
        this.student = student;
        this.scanner = new Scanner(System.in);
    }
    public void showMenu() {
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║         STUDENT MENU                        ║");
        System.out.println("║  Welcome, " + String.format("%-31s", student.getFirst_name() + " " + student.getLast_name()) + "║");
        System.out.println("║  Student #: " + String.format("%-31s", student.getStudent_id()) + "║");
        System.out.println("╚═════════════════════════════════════════════╝");
        System.out.println();

        boolean running = true;
        while (running) {
            System.out.println("1. View My Courses and Grades");
            System.out.println("2. View My Profile");
            System.out.println("3. Edit Profile");
            System.out.println("4. Settings");
            System.out.println("5. Logout");
            System.out.println();
            System.out.print("Enter your choice: ");
            try{
                int choice = Integer.parseInt(scanner.nextLine());
                running = StudentRouter.studentRouteToOption(choice, student);
            }catch(NumberFormatException e){
                System.out.println("Please enter a number");
            }catch (InvalidChoiceException ex ){
                System.out.println("✗ " + ex.getMessage());
            }

            }
        }


    }

