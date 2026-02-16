package com.marius.sms.frontend.cli.router;

import com.marius.sms.backend.entities.Teacher;
import com.marius.sms.backend.exception.InvalidChoiceException;
import com.marius.sms.frontend.cli.views.student.StudentGradesView;
import com.marius.sms.frontend.cli.views.student.StudentProfileView;
import com.marius.sms.frontend.cli.views.teacher.TeacherCoursesView;

public class TeacherRouter {

    public static boolean teacherRouteToView(int choice, Teacher teacher){
        switch (choice) {
            case 1:
                TeacherCoursesView teacherCoursesView= new TeacherCoursesView(teacher);
                teacherCoursesView.show();
                return true;
            case 8:
                return false;
            default:
                throw new InvalidChoiceException("Invalid choice. Choose a number from 1 to 5");

        }

    }
}
