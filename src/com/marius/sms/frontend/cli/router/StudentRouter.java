package com.marius.sms.frontend.cli.router;

import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.exception.InvalidChoiceException;
import com.marius.sms.frontend.cli.views.student.StudentGradesView;
import com.marius.sms.frontend.cli.views.student.StudentProfileView;

public class StudentRouter {
    public static boolean studentRouteToOption(int choice, Student student){
        switch (choice) {
            case 1:
                StudentGradesView studentGradesView = new StudentGradesView(student);
                studentGradesView.show();
                return true;
            case 2:
                StudentProfileView studentProfileView  = new StudentProfileView(student);
                studentProfileView.show();
                return true;
            case 5:
                return false;
            default:
                throw new InvalidChoiceException("Invalid choice. Choose a number from 1 to 5");


        }
    }
}
