package com.marius.sms.frontend.cli.router;

import com.marius.sms.backend.entities.Teacher;
import com.marius.sms.backend.exception.InvalidChoiceException;
import com.marius.sms.frontend.cli.views.teacher.TeacherCoursesView;

public class TeacherRouter implements ObjectRouter<Teacher> {

    public boolean routeToView(int choice, Teacher teacher){
        switch (choice) {
            case 1:
                TeacherCoursesView teacherCoursesView= new TeacherCoursesView(teacher);
                teacherCoursesView.show();
                return true;
            case 7:
                return false;
            default:
                throw new InvalidChoiceException("Invalid choice. Choose a number from 1 to 7");

        }

    }

}
