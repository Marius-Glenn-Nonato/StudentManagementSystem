package com.marius.sms.backend.security;

import com.marius.sms.backend.dao.UserDAO;
import com.marius.sms.backend.dao.StudentDAO;
import com.marius.sms.backend.dao.TeacherDAO;
import com.marius.sms.backend.entities.User;
import com.marius.sms.backend.entities.Student;
import com.marius.sms.backend.entities.Teacher;
import com.marius.sms.backend.exception.AuthenticationException;
import java.util.Optional;
import java.util.logging.Logger;

public class AuthService {
    private static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());
    private final UserDAO userDAO;
    private final StudentDAO studentDAO;
    private final TeacherDAO teacherDAO;

    public AuthService(UserDAO userDAO, StudentDAO studentDAO, TeacherDAO teacherDAO) {
        this.userDAO = userDAO;
        this.studentDAO = studentDAO;
        this.teacherDAO = teacherDAO;
    }

    public User login(String usernameOrEmail, String password) throws AuthenticationException {
        // Try username first
        Optional<User> userOpt = userDAO.getUserLoginUsingUserName(usernameOrEmail);
        
        // If not found by username, try email
        if (!userOpt.isPresent()) {
            userOpt = userDAO.getUserLoginUsingEmail(usernameOrEmail);
        }

        if (!userOpt.isPresent()) {
            LOGGER.warning("Login attempt failed: User not found with username or email " + usernameOrEmail);
            throw new AuthenticationException("Invalid credentials");
        }

        User user = userOpt.get();
        if (!PasswordChecker.verifyPassword(password, user.getPassword_hash())) {
            LOGGER.warning("Login attempt failed: Invalid password for " + usernameOrEmail);
            throw new AuthenticationException("Invalid credentials");
        }

        LOGGER.info("User " + usernameOrEmail + " logged in successfully");
        return user;
    }
    public Object getCompleteUserData(User user) {
        if (RoleChecker.isStudent(user)) {
            Optional<Student> student = studentDAO.getStudentByUserId(user.getUser_id());
            if (student.isPresent()) {
                return student.get();
            }
        } else if (RoleChecker.isTeacher(user)) {
            Optional<Teacher> teacher = teacherDAO.getTeacherByUserId(user.getUser_id());
            if (teacher.isPresent()) {
                return teacher.get();
            }
        }
        // For ADMIN (1) and REGISTRAR (2), return the base User object
        return user;
    }
}
