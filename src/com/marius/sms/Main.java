package com.marius.sms;

import com.marius.sms.backend.dao.RoleDAO;
import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.dto.StudentCourseDTO;
import com.marius.sms.backend.entities.*;
import com.marius.sms.backend.security.PasswordChecker;
import com.marius.sms.backend.service.StudentService;
import com.marius.sms.frontend.cli.menus.MainMenuCLI;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//
//        String passcode = PasswordChecker.hashPassword("randy");
//        System.out.println(passcode);
//        // Start the SMS application
//        MainMenuCLI mainMenuCLI = new MainMenuCLI();
//        mainMenuCLI.start();


//        // Previous test code - commented out
//        RoleDAO dao = new RoleDAO();
//        List<Role> roles = dao.getAll();
//        for (Role role : roles) {
//            System.out.println(role);
//        }

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

        StudentService studentService = new StudentService(); // assuming your method is in this class
        Integer studentId = 1; // change to any existing student_id in your DB

        List<StudentCourseDTO> courses = studentService.getCoursesOfStudent(studentId);

        // Print out the courses and their schedules
        for (StudentCourseDTO dto : courses) {
            Enrollment enrollment = dto.getEnrollment();
            CourseOffering offering = enrollment.getCourse_offering();
            Course course = offering.getCourse();
            Teacher teacher = offering.getTeacher();
            Term term = offering.getTerm();

            System.out.println("------------------------------------------------");
            System.out.println("Course: " + course.getCourseCode() + " - " + course.getCourseName());
            System.out.println("Section: " + offering.getSectionCode());
            System.out.println("Teacher: " + teacher.getFirst_name());
            System.out.println("Term: " + term.getStartDate() + " to " + term.getEndDate());
            System.out.println("Status: " + enrollment.getStatus());
            System.out.println("Final Grade: " + enrollment.getFinalGrade());

            System.out.println("Schedule:");
            for (OfferingSchedule schedule : dto.getOfferingSchedules()) {
                System.out.println("  " + schedule.getDayOfWeek() + " " +
                        schedule.getStartTime() + " - " +
                        schedule.getEndTime() + " at " + schedule.getRoom());
            }
        }


    }
}
