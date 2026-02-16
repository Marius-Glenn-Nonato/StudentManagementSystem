package com.marius.sms.frontend.cli.menus;

import com.marius.sms.backend.dao.UserDAO;
import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.dao.TeacherDAO;
import com.marius.sms.backend.entities.User;
import com.marius.sms.backend.security.AuthService;
import com.marius.sms.frontend.cli.router.MainMenuRouter;
import com.marius.sms.frontend.cli.util.CLIUtils;

import java.util.Scanner;
import java.util.logging.Logger;

public class MainMenuCLI {
    private static final Logger LOGGER = Logger.getLogger(MainMenuCLI.class.getName());
    private static Scanner scanner = new Scanner(System.in);
    private final AuthService authService;

    public MainMenuCLI(){
        this.authService = new AuthService(new UserDAO(), new StudentDAO(), new TeacherDAO());
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   Student Management System - SMS v1.0     ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();


        try{
            CLIUtils cliUtils = new CLIUtils();
            User authenticatedUser = cliUtils.showLoginPrompt();
            //authenticatedUser = only user data
            if(authenticatedUser != null){
                //If user exists, get the role and complete User and User + Subclass data
                Object completeUserData = authService.getCompleteUserData(authenticatedUser);
                MainMenuRouter.routeToProperUserMenu(authenticatedUser, completeUserData);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            scanner.close();
        }
    }
}
