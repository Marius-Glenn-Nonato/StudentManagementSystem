package com.marius.sms.frontend.cli.router;

import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.exception.InvalidChoiceException;
import com.marius.sms.frontend.cli.views.student.StudentAttendanceView;
import com.marius.sms.frontend.cli.views.student.StudentCourseView;
import com.marius.sms.frontend.cli.views.student.StudentProfileView;

public class StudentRouter implements ObjectRouter<Student> {
    public boolean routeToView(int choice, Student student){
        switch (choice) {
            case 1:
                StudentCourseView studentCourseView = new StudentCourseView(student);
                studentCourseView.show();
                return true;
            case 2:
                StudentAttendanceView studentAttendanceView = new StudentAttendanceView(student);
                studentAttendanceView.show();
                return true;
            case 5:
                StudentProfileView studentProfileView  = new StudentProfileView(student);
                studentProfileView.show();
                return true;
            case 7:
                return false;
            default:
                throw new InvalidChoiceException("Invalid choice. Choose a number from 1 to 7");

        }
    }
}
