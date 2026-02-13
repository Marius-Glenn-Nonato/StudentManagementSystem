package com.marius.sms;

import com.marius.sms.backend.dao.RoleDAO;
import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.entities.Role;
import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.entities.User;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {


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
    }
}
