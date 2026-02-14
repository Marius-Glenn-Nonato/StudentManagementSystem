package com.marius.sms.frontend.cli;

import com.marius.sms.backend.entities.User;
import java.util.Scanner;

public class RegistrarMenu {
    private final User registrar;
    private final Scanner scanner;

    public RegistrarMenu(User registrar) {
        this.registrar = registrar;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display the registrar menu
     */
    public void showMenu() {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║       REGISTRAR MENU                      ║");
        System.out.println("║  Welcome, " + String.format("%-33s", registrar.getUsername()) + "║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println();

        boolean running = true;
        while (running) {
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Teachers");
            System.out.println("3. Manage Courses");
            System.out.println("4. View Enrollments");
            System.out.println("5. Settings");
            System.out.println("6. Logout");
            System.out.println();
            System.out.print("Select an option: ");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    System.out.println("Manage Students - Not yet implemented");
                    break;
                case "2":
                    System.out.println("Manage Teachers - Not yet implemented");
                    break;
                case "3":
                    System.out.println("Manage Courses - Not yet implemented");
                    break;
                case "4":
                    System.out.println("View Enrollments - Not yet implemented");
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
}
