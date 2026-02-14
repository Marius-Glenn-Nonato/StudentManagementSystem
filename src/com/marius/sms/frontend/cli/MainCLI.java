package com.marius.sms.frontend.cli;

import com.marius.sms.backend.dao.UserDAO;
import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.dao.TeacherDAO;
import com.marius.sms.backend.entities.User;
import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.entities.Teacher;
import com.marius.sms.backend.exception.AuthenticationException;
import com.marius.sms.backend.security.AuthService;
import com.marius.sms.backend.security.RoleChecker;

import java.util.Scanner;
import java.util.logging.Logger;

public class MainCLI {
    private static final Logger LOGGER = Logger.getLogger(MainCLI.class.getName());
    private final AuthService authService;
    private final Scanner scanner;

    public MainCLI() {
        this.authService = new AuthService(new UserDAO(), new StudentDAO(), new TeacherDAO());
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   Student Management System - SMS v1.0     ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();

        try {
            User authenticatedUser = login();
            if (authenticatedUser != null) {
                //Since the user already has role_id, this is where the student/teacher info gets fetched
                Object completeUserData = authService.getCompleteUserData(authenticatedUser);
                routeToMenu(authenticatedUser, completeUserData);
            }
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            LOGGER.severe("Application error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private User login() {
        System.out.println("═══ LOGIN ═══");
        System.out.println();

        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                System.out.print("Username or Email: ");
                String usernameOrEmail = scanner.nextLine().trim();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                User user = authService.login(usernameOrEmail, password);
                System.out.println();
                System.out.println("✓ Login successful! Welcome, " + user.getUsername() + "!");
                System.out.println();
                return user;

            } catch (AuthenticationException e) {
                attempts++;
                System.out.println("✗ " + e.getMessage());
                if (attempts < maxAttempts) {
                    System.out.println("Attempts remaining: " + (maxAttempts - attempts));
                }
                System.out.println();
            }
        }

        System.out.println("✗ Maximum login attempts exceeded. Exiting...");
        return null;
    }

    private void routeToMenu(User user, Object completeUserData) {
        if (RoleChecker.isAdmin(user)) {
            System.out.println("Routing to Admin Menu...");
            AdminMenu adminMenu = new AdminMenu(user);
            adminMenu.showMenu();
        } else if (RoleChecker.isRegistrar(user)) {
            System.out.println("Routing to Registrar Menu...");
            RegistrarMenu registrarMenu = new RegistrarMenu(user);
            registrarMenu.showMenu();
        } else if (RoleChecker.isStudent(user)) {
            System.out.println("Routing to Student Menu...");
            Student student = (Student) completeUserData;
            StudentMenu studentMenu = new StudentMenu(student);
            studentMenu.showMenu();
        } else if (RoleChecker.isTeacher(user)) {
            System.out.println("Routing to Teacher Menu...");
            // Teacher teacher = (Teacher) completeUserData;
            // TeacherMenu teacherMenu = new TeacherMenu(teacher);
            // teacherMenu.showMenu();
            System.out.println("Teacher Menu not yet implemented.");
        } else {
            System.out.println("✗ Unknown role: " + user.getRole_id());
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public AuthService getAuthService() {
        return authService;
    }
}
