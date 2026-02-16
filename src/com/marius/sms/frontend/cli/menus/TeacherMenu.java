package com.marius.sms.frontend.cli.menus;

import com.marius.sms.backend.entities.Teacher;
import com.marius.sms.backend.exception.InvalidChoiceException;
import com.marius.sms.frontend.cli.router.TeacherRouter;

import java.util.Scanner;

public class TeacherMenu implements Menu {
    private final Teacher teacher;
    private final Scanner scanner;
    private TeacherRouter teacherRouter;
    public TeacherMenu(Teacher teacher) {
        this.teacher = teacher;
        this.scanner = new Scanner(System.in);
        this.teacherRouter = new TeacherRouter();
    }
    //t.teacher_id, t.first_name, t.last_name, t.hire_date, t.user_id, u.username, u.email, u.password_hash, u.role_id, u.created_at
    public void showMenu() {
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║         TEACHER MENU                        ║");
        System.out.println("║  Welcome, " + String.format("%-31s", teacher.getFirst_name() + " " + teacher.getLast_name()) + "║");
        System.out.println("║  Teacher #: " + String.format("%-31s", teacher.getTeacher_id()) + "║");
        System.out.println("╚═════════════════════════════════════════════╝");
        System.out.println();

        boolean running = true;
        while (running) {
            System.out.println("1. View My Classes");
            System.out.println("2. View Students in a Class - Not yet Implemented");
            System.out.println("3. Update Grades in a Class - Not yet Implemented");
            System.out.println("4. Manage Attendance - Not yet Implemented");
            System.out.println("5. View My Profile - Not yet Implemented");
            System.out.println("6. Request Edit Profile - Not yet Implemented"); //This is because in the logic using GUI, there's an additional button to request edit then you can modify text fields there
            System.out.println("7. Logout");
            System.out.println();
            System.out.print("Enter your choice: ");
            try{
                int choice = Integer.parseInt(scanner.nextLine());
                running = teacherRouter.routeToView(choice, teacher);
            }catch(NumberFormatException e){
                System.out.println("Please enter a number");
            }catch (InvalidChoiceException ex){
                System.out.println("✗ " + ex.getMessage());
            }

        }
    }
}
