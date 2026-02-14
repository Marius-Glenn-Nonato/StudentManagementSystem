package com.marius.sms.frontend.cli.util;

import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.dao.TeacherDAO;
import com.marius.sms.backend.dao.UserDAO;
import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.entities.User;
import com.marius.sms.backend.exception.AuthenticationException;
import com.marius.sms.backend.security.AuthService;
import com.marius.sms.backend.security.RoleChecker;
import com.marius.sms.frontend.cli.AdminMenu;
import com.marius.sms.frontend.cli.MainCLI;
import com.marius.sms.frontend.cli.RegistrarMenu;
import com.marius.sms.frontend.cli.StudentMenu;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.logging.Logger;

public class CLIUtils {
    private static final Logger LOGGER = Logger.getLogger(MainCLI.class.getName());
    private AuthService authService;
    private Scanner scanner;



    public CLIUtils() {
        this.authService = new AuthService(new UserDAO(), new StudentDAO(), new TeacherDAO());
        this.scanner = new Scanner(System.in);
    }

    public User showLoginPrompt() {
        System.out.println("═══ LOGIN ═══");
        System.out.println();

        int maxAttempts = 5;
        int attempts = 0;

        while(attempts < maxAttempts) {
            try {
                System.out.print("Username/Email: ");
                String usernameOrEmail = scanner.nextLine().trim();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                User user = authService.login(usernameOrEmail, password);
                System.out.println();
                System.out.println("Login Successful!: Welcome, " + user.getUsername() + "!");

                //If the user exists, exit from the entire loop and return the user object
                return user;
            }catch(AuthenticationException e) {
                attempts++;
                System.out.println();
                System.out.println("✗ " + e.getMessage());
                int attemptsRemaining = maxAttempts - attempts;
                if(attemptsRemaining > 0) {
                    System.out.println("Attempts remaining: " + attemptsRemaining);
                    System.out.println("Please try again.");
                } else {
                    System.out.println("Maximum login attempts reached. Exiting...");
                }
                System.out.println();
            }
        }
        return null;
    }



}
