package com.marius.sms.frontend.cli.views.student;

import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.entities.Student;

public class StudentEditProfileDetailsView {
    private final Student student;
    private final StudentDAO studentDAO;

    public StudentEditProfileDetailsView(Student student) {
        this.student = student;
        this.studentDAO = new StudentDAO();
    }
    public void show(){

    }
}
