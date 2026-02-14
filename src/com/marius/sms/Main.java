package com.marius.sms;

import com.marius.sms.backend.dao.RoleDAO;
import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.dao.UserDAO;
import com.marius.sms.backend.entities.Role;
import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.entities.User;
import com.marius.sms.backend.security.PasswordChecker;
import com.marius.sms.frontend.cli.MainCLI;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

//        String passcode = PasswordChecker.hashPassword("marius");
//        System.out.println(passcode);
//        // Start the SMS application
//        MainCLI mainCLI = new MainCLI();
//        mainCLI.start();


        // Previous test code - commented out
        RoleDAO dao = new RoleDAO();
        List<Role> roles = dao.getAll();
        for (Role role : roles) {
            System.out.println(role);
        }

        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getAll();
        for (Student student : students) {
            System.out.println(student);
        }
        Optional<Student> studentOptional = studentDAO.getStudentByStudentNumber("S001");
        if(studentOptional.isPresent()) {
            Student student = studentOptional.get();
            System.out.println(student);
        }
        UserDAO userDAO = new UserDAO();
        Optional<User> user = userDAO.getUserLoginUsingUserName("marius123");
        if(user.isPresent()) {
            System.out.println(user.get());
        }

    }
}
