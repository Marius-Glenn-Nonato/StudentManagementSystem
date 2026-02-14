package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.Enrollment;
import com.marius.sms.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class EnrollmentDAO {
    private static final Logger LOGGER = Logger.getLogger(EnrollmentDAO.class.getName());

    private static final String TABLE_NAME = "sms.enrollments";

    private static final String SQL_GET_ALL_ENROLLMENTS_OF_STUDENT = "SELECT e.enrollment_id, e.student_id, e.grade, e.course_id  FROM "+TABLE_NAME +" e WHERE student_id=?";
    private static final String SQL_INSERT_ENROLLMENT_QUERY = "INSERT INTO "+TABLE_NAME+" (student_id, grade, course_id) VALUES (?,?,?)";

    public List<Enrollment> getAllEnrollmentsOfStudent(Integer student_id) {
        List<Enrollment> enrollments = new ArrayList<>();
        try(
                Connection connection = DatabaseUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_ENROLLMENTS_OF_STUDENT)
        ){
            preparedStatement.setInt(1, student_id);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                enrollments = processResultSet(resultSet);
            }
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("EnrollmentDAO.getAllEnrollmentsOfStudent", e, LOGGER);
        }
        return enrollments;
    }
    public Enrollment create(Enrollment entity){
        return null;
    }
    private List<Enrollment> processResultSet(ResultSet rs) throws SQLException {
       return null;
    }
}
