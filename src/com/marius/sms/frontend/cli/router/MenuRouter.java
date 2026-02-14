package com.marius.sms.frontend.cli.router;

import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.entities.Teacher;
import com.marius.sms.backend.entities.User;
import com.marius.sms.backend.security.RoleChecker;
import com.marius.sms.frontend.cli.StudentMenu;
import com.marius.sms.frontend.cli.TeacherMenu;

public class MenuRouter {
    public MenuRouter(){
    }

    public static void routeToProperUserMenu(User user, Object completedUserData){
        if(RoleChecker.isStudent(user)){
            Student student = (Student) completedUserData;
            StudentMenu studentMenu = new StudentMenu(student);
            studentMenu.showMenu();
        } else if (RoleChecker.isTeacher(user)) {
            Teacher teacher = (Teacher) completedUserData;
            TeacherMenu teacherMenu = new TeacherMenu(teacher);
            teacherMenu.showMenu();
        }
        else{
            System.out.println("Unknown role ");
        }
    }
}
