package com.marius.sms.frontend.cli.router;

import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.exception.InvalidChoiceException;
import com.marius.sms.frontend.cli.views.student.StudentGradesView;

public class StudentRouter {
    public static boolean studentRouteToOption(int choice, Student student){
        switch (choice) {
            case 1:
                StudentGradesView studentGradesView = new StudentGradesView(student);
                studentGradesView.show();
                return true;
            case 6:
                return false;
            default:
                throw new InvalidChoiceException("Invalid choice. Choose a number from 1 to 6");


        }
    }
}
