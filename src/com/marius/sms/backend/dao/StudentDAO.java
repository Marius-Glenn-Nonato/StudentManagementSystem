package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.Student;
import com.marius.sms.util.DatabaseUtils;
import com.marius.sms.util.DateUtils;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class StudentDAO implements DAO<Student, Integer> {
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());
    private static final String TABLE_NAME = "sms.students";
    private static final String SQL_GET_ALL_STUDENTS_QUERY = "SELECT * FROM "+TABLE_NAME;
    private static final String SQL_GET_STUDENT_BY_ID_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE student_id = ?";
    private static final String SQL_GET_STUDENT_BY_LAST_NAME_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE last_name = ?";
    private static final String SQL_GET_STUDENT_BY_FIRSTANDLAST_NAME_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE first_name = ? AND last_name = ?";
    private static final String SQL_GET_STUDENT_BY_EMAIL_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE email = ?";
    private static final String SQL_GET_STUDENT_BY_STUDENT_NUMBER_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE student_number = ?";

    private static final String SQL_INSERT_STUDENT_QUERY = "INSERT INTO "+TABLE_NAME+" (student_number, first_name, last_name, email, date_of_birth, created_at) VALUES (?,?,?,?,?,?)";

    private static final String SQL_UPDATE_STUDENT_EMAIL_QUERY = "UPDATE "+TABLE_NAME+"SET email=? WHERE student_number=?";
    private static final String SQL_UPDATE_STUDENT_FIRST_NAME_QUERY = "UPDATE "+TABLE_NAME+"SET first_name=? WHERE student_number=?";
    private static final String SQL_UPDATE_STUDENT_LAST_NAME_QUERY = "UPDATE "+TABLE_NAME+"SET last_name=? WHERE student_number=?";
    private static final String SQL_UPDATE_STUDENT_DATE_OF_BIRTH_QUERY = "UPDATE "+TABLE_NAME+"SET date_of_birth=? WHERE student_number=?";

    private static final String SQL_DELETE_STUDENT_QUERY = "DELETE FROM "+TABLE_NAME+" WHERE student_number=?";


    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try(
                Connection connection = DatabaseUtils.getConnection();
                Statement statement = connection.createStatement();
                ){
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_STUDENTS_QUERY);
            students = processResultSet(resultSet);

        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("StudentDAO.getAll()",e,LOGGER);
        }
        System.out.println("Connection closed " + getClass().getName());
        return List.of();
    }

    //----------------------INSERT-----------------------------
    @Override
    public Student create(Student entity) {
        Connection connection = null;
        try{
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_STUDENT_QUERY, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, entity.getStudent_number());
                preparedStatement.setString(2, entity.getFirst_name());
                preparedStatement.setString(3, entity.getLast_name());
                preparedStatement.setString(4, entity.getEmail());
                preparedStatement.setDate(5, DateUtils.toSqlDate(entity.getDate_of_birth()));
                preparedStatement.setTimestamp(6, DateUtils.getCurrentTimestamp());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Failed to insert new student.");
                }
                //Get generated key
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if(generatedKeys.next()) {
                        entity.setStudent_id(generatedKeys.getInt(1));
                    }
                }
                connection.commit();
                System.out.println("Student created with id " + entity.getStudent_id());
                return entity;
            }
        }catch (SQLException e){
            if(connection == null){
                try{
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("StudentDAO.create().rollback",ex,LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("StudentDAO.create()",e,LOGGER);
            return null;
        }finally {
            if(connection != null){
                try{
                    System.out.println("Connection closed " + getClass().getName());
                    connection.close();
                } catch (SQLException ez) {
                    DatabaseUtils.handleSQLException("StudentDAO.create().close().connectionClose",ez,LOGGER);
                }
            }
        }
    }


    //----------------------SELECT-----------------------------
    //STUDENT GET BY ID
    @Override
    public Optional<Student> getOne(Integer studentId) {
        try(
                Connection connection = DatabaseUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_STUDENT_BY_ID_QUERY);
                ) {
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = processResultSet(resultSet);
            if(!students.isEmpty()) {
                System.out.println("Student found with id " + studentId);
                System.out.println("Connection closed " + getClass().getName());
                return Optional.of(students.get(0));
            }
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("StudentDAO.getOne() (BY ID)",e,LOGGER);
        }
        System.out.println("Connection closed null" + getClass().getName());
        return Optional.empty();
    }

    //STUDENT GET BY STUDENT NUMBER
    public Optional<Student> getStudentByStudentNumber(Integer studentNumber) {
        try(
                Connection connection = DatabaseUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_STUDENT_BY_STUDENT_NUMBER_QUERY);
                ){
            preparedStatement.setInt(1, studentNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = processResultSet(resultSet);
            if(!students.isEmpty()) {
                System.out.println("Student found with student number " + studentNumber);
                System.out.println("Connection closed " + getClass().getName());
                return Optional.of(students.get(0));
            }
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("StudentDAO.getStudentByStudentNumber()",e,LOGGER);
        }
        System.out.println("Connection closed null" + getClass().getName());
        return Optional.empty();
    }

    //--------------------UPDATE------------------------------

    @Override
    public Student update(Student entity) {
       return null;
    }

    public Student updateEmail(Student entity) {
        Connection connection = null;
        try{
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);

            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STUDENT_EMAIL_QUERY)){
                preparedStatement.setString(1, entity.getEmail());
                preparedStatement.setString(2, entity.getStudent_number());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("StudentDAO.updateEmail() failed. Student not found with student number "+ entity.getStudent_number());

                }
            }
            connection.commit();
            System.out.println("Student updated email with id " + entity.getStudent_number());
            return entity;
        } catch (SQLException e) {
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("StudentDAO.update().rollback",ex,LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("StudentDAO.update()",e,LOGGER);
            return null;
        }finally {
            if(connection != null){
                try {
                    System.out.println("Connection closed " + getClass().getName());
                    connection.close();
                } catch (SQLException e) {
                    DatabaseUtils.handleSQLException("StudentDAO.update().close()",e,LOGGER);
                }
            }
        }
    }
    //-------------------DELETE-------------------------------
    @Override
    public boolean delete(Integer integer) {
        return false;
    }
    private List<Student> processResultSet(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student();
            student.setStudent_id(resultSet.getInt("student_id"));
            student.setStudent_number(resultSet.getString("student_number"));
            student.setFirst_name(resultSet.getString("first_name"));
            student.setLast_name(resultSet.getString("last_name"));
            student.setEmail(resultSet.getString("email"));
            student.setDate_of_birth(DateUtils.toLocalDate(resultSet.getDate("date_of_birth")));
            student.setCreated_at(DateUtils.toLocalDateTime(resultSet.getTimestamp("created_at")));
        }
        return students;
    }
}
