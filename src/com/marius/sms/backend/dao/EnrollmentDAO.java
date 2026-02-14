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
        Connection connection = null;
        try{
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ENROLLMENT_QUERY, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setInt(1, entity.getStudent_id());
                preparedStatement.setDouble(2, entity.getGrade());
                preparedStatement.setString(3, entity.getCourse_id());
                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows == 0){
                    throw new SQLException("Failed to insert new enrollment");
                }
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if(generatedKeys.next()){
                        entity.setEnrollment_id(generatedKeys.getInt(1));
                    }
                }
                connection.commit();
                DatabaseUtils.printNewEntityCreated(entity, entity.getEnrollment_id());
                return entity;
            }
        }catch (SQLException e){
            if(connection != null){
                try{
                    connection.rollback();
                }catch(SQLException ex){
                    DatabaseUtils.handleSQLException("EnrollmentDAO.create().rollback",ex,LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("EnrollmentDAO.create().rollback",e,LOGGER);
            return null;
        }finally{
            if(connection != null) {
                try {
                    DatabaseUtils.printSQLConnectionClose("EnrollmentDAO.create()", Enrollment.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ez) {
                    DatabaseUtils.handleSQLException("EnrollmentDAO.create().finally.close()", ez, LOGGER);
                }
            }
        }
    }
    private List<Enrollment> processResultSet(ResultSet rs) throws SQLException {
        List<Enrollment> enrollments = new ArrayList<>();
        while(rs.next()) {
            Enrollment enrollment = new Enrollment();
            enrollment.setEnrollment_id(rs.getInt("enrollment_id"));
            enrollment.setStudent_id(rs.getInt("student_id"));
            enrollment.setGrade(rs.getDouble("grade"));
            enrollment.setCourse_id(rs.getString("course_id"));
            enrollments.add(enrollment);
        }
        return enrollments;
    }
}
