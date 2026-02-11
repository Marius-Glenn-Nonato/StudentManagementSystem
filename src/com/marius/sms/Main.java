package com.marius.sms;

import com.marius.sms.backend.dao.RoleDAO;
import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.entities.Role;
import com.marius.sms.backend.entities.Student;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RoleDAO dao = new RoleDAO();
        List<Role> roles = dao.getAll();
        for (Role role : roles) {
            System.out.println(role);
        }

        StudentDAO dao1 = new StudentDAO();
        List<Student> students = dao1.getAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
