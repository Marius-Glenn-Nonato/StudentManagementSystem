package com.marius.sms;

import com.marius.sms.frontend.cli.menus.MainMenuCLI;

public class Main {
    public static void main(String[] args) {

//        String passcode = PasswordChecker.hashPassword("marius");
//        System.out.println(passcode);
//        // Start the SMS application
        MainMenuCLI mainMenuCLI = new MainMenuCLI();
        mainMenuCLI.start();


//        // Previous test code - commented out
//        RoleDAO dao = new RoleDAO();
//        List<Role> roles = dao.getAll();
//        for (Role role : roles) {
//            System.out.println(role);
//        }
//
//        StudentDAO studentDAO = new StudentDAO();
//        List<Student> students = studentDAO.getAll();
//        for (Student student : students) {
//            System.out.println(student);
//        }
//        Optional<Student> studentOptional = studentDAO.getOne(1);
//        if(studentOptional.isPresent()) {
//            Student student = studentOptional.get();
//            System.out.println(student);
//        }
//
//        UserDAO userDAO = new UserDAO();
//        Optional<User> user = userDAO.getUserLoginUsingUserName("marius123");
//        if(user.isPresent()) {
//            System.out.println(user.get());
//        }
//
//        TeacherDAO teacherDAO = new TeacherDAO();
//        Optional<Teacher> teacher = teacherDAO.getOne(1);
//        if(teacher.isPresent()) {
//            System.out.println(teacher.get());
//        }
//        List<Teacher> teachers = teacherDAO.getAll();
//        for(Teacher teacher1: teachers){
//            System.out.println(teacher1);
//        }
//        CourseDAO courseDAO = new CourseDAO();
//        Optional<Course> course = courseDAO.getCourseByCourseId("CS");
//        if(course.isPresent()) {
//            System.out.println(course.get());
//        }
    }
}
