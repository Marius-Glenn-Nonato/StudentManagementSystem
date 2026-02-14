package com.marius.sms.frontend.cli;

import com.marius.sms.backend.entities.User;
import java.util.Scanner;

public class AdminMenu {
    private final User admin;
    private final Scanner scanner;

    public AdminMenu(User admin) {
        this.admin = admin;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display the admin menu
     */
    public void showMenu() {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║         ADMIN MENU                        ║");
        System.out.println("║  Welcome, " + String.format("%-33s", admin.getUsername()) + "║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println();

        boolean running = true;
        while (running) {
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Roles");
            System.out.println("3. View Audit Logs");
            System.out.println("4. Settings");
            System.out.println("5. Logout");
            System.out.println();
            System.out.print("Select an option: ");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    System.out.println("Manage Users - Not yet implemented");
                    break;
                case "2":
                    System.out.println("Manage Roles - Not yet implemented");
                    break;
                case "3":
                    System.out.println("View Audit Logs - Not yet implemented");
                    break;
                case "4":
                    System.out.println("Settings - Not yet implemented");
                    break;
                case "5":
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
