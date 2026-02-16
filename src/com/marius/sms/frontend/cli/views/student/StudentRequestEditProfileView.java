package com.marius.sms.frontend.cli.views.student;

import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.service.StudentService;

import java.util.Scanner;

public class StudentRequestEditProfileView {
    private final Student student;
    private final StudentDAO studentDAO;
    private final Scanner scanner;
    private StudentService studentService;

    public StudentRequestEditProfileView(Student student) {
        this.student = student;
        this.studentDAO = new StudentDAO();
        this.scanner = new Scanner(System.in);
    }
    //You already have complete student info: s.student_id, s.first_name, s.last_name,s.date_of_birth,s.user_id, u.username, u.user_email, u.password_hash, u.role_id, u.user_created_at
    public void show() {
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║              STUDENT PROFILE                ║");
        System.out.println("╚═════════════════════════════════════════════╝");

        // Use printf to align columns
        System.out.printf("%-15s : %s%n", "Student ID", student.getStudent_id());
        System.out.printf("%-15s : %s %s%n", "Name", student.getFirst_name(), student.getLast_name());
        System.out.printf("%-15s : %s%n", "Username", student.getUsername());
        System.out.printf("%-15s : %s%n", "Email", student.getUser_email());
        System.out.printf("%-15s : %s%n", "Date of Birth", student.getDate_of_birth());
        System.out.printf("%-15s : %s%n", "Created At", student.getUser_created_at());
        System.out.println("──────────────────────────────────────────────");

        //Only implement code below when you've implemented requests table in sql

        //
//        boolean running = true;
//        while (running) {
//            System.out.println("1. Edit Student First Name");
//            System.out.println("2. Edit Student Last Name");
//            System.out.println("3 Edit Student Username");
//            System.out.println("4 Edit Student Email");
//            System.out.println("5 Edit Student Password");
//            System.out.println("6 Edit Student Date of Birth");
//            System.out.println("7 Back");
//            System.out.print("Enter your choice: ");
//            try{
//                int choice = Integer.parseInt(scanner.nextLine());
//                running = routeToEditOption(choice, student);
//            }catch (NumberFormatException e){
//                System.out.println("Please enter a number");
//            }catch (InvalidChoiceException ex){
//                System.out.println("✗ " + ex.getMessage());
//            }
//        }
    }

//    private boolean routeToEditOption(int choice, Student student) {
//        switch (choice) {
//            case 1:
//                editFirstName(student);
//                break;
//            default:
//                throw new InvalidChoiceException("Invalid choice. Choose a number from 1 to 7");
//        }
//        return true;
//    }
//
//    private void editFirstName(Student student) {
//        System.out.print("Enter NEW first name: ");
//        String newFirstName = scanner.nextLine().trim();
//
//        if(student.getFirst_name().equals(newFirstName)){
//            System.out.println("New First name cannot be the same as the previous name");
//            return;
//        }
//
//        if(newFirstName.isEmpty()){
//            System.out.println("New First name cannot be empty");
//            return;
//        }
//
//    }

}
