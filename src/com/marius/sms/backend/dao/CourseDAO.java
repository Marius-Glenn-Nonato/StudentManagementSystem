package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.Course;
import com.marius.sms.util.DatabaseUtils;
import com.marius.sms.util.DateUtils;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CourseDAO implements DAO<Course, Integer> {
    private static final Logger LOGGER = Logger.getLogger(CourseDAO.class.getName());
    private static final String TABLE_NAME = "sms.courses";
    private static final String SQL_GET_ALL_COURSES = "SELECT course_id, course_name, credits, created_at, teacher_id FROM " +TABLE_NAME;
    private static final String SQL_GET_COURSE_BY_ID = "SELECT course_id, course_name, credits, created_at, teacher_id FROM " +TABLE_NAME + " WHERE course_id LIKE ?";

    private static final String SQL_INSERT_COURSE_QUERY = "INSERT INTO "+ TABLE_NAME+" (course_id, course_name, credits, teacher_id) VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE_COURSE = "UPDATE "+TABLE_NAME+ " SET course_id = ?, course_name=?, credits=?, teacher_id=? WHERE course_id = ?";
    private static final String SQL_DELETE_COURSE_ID = "DELETE FROM "+TABLE_NAME+" WHERE course_id = ?";
    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        try(
                Connection connection = DatabaseUtils.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_COURSES);
                ) {
            courses= processResultSet(resultSet);
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("CourseDAO.getAll()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("CourseDAO.getAll()", CourseDAO.class);
        return courses;
    }



    @Override
    public Course create(Course entity) {
        Connection connection = null;
        try{
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_COURSE_QUERY)) {
                preparedStatement.setString(1, entity.getCourse_id());
                preparedStatement.setString(2, entity.getCourse_name());
                preparedStatement.setInt(3, entity.getCredits());
                preparedStatement.setInt(4, entity.getTeacher_id());

                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows == 0) {
                    throw new SQLException("CourseDAO.create(): Course could not be created");
                }

                connection.commit();
                DatabaseUtils.printNewEntityCreated(entity, entity.getCourse_id());
                return entity;
            }

        }catch (SQLException e){
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ez) {
                    DatabaseUtils.handleSQLException("CourseDAO.create()", ez, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("CourseDAO.create().null", e, LOGGER);
            return null;
        }finally {
            if(connection != null){
                try{
                    DatabaseUtils.printSQLConnectionClose("CourseDAO.create()", CourseDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                }catch (SQLException ex){
                    DatabaseUtils.handleSQLException("CourseDAO.create().finally.close()", ex, LOGGER);
                }
            }

        }
    }


    public Optional<Course> getCourseByCourseId(String courseId) {
        try(Connection connection = DatabaseUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COURSE_BY_ID)){
            preparedStatement.setString(1, "%" + courseId + "%");
            ResultSet rs = preparedStatement.executeQuery();
            List<Course> courses = processResultSet(rs);
            if(!courses.isEmpty()) {
                DatabaseUtils.printSQLConnectionClose("CourseDAO.getCourseByCourseCode()", CourseDAO.class);
                return Optional.of(courses.get(0));
            }

        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("CourseDAO.getCourseByCourseCode()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("CourseDAO.getCourseByCourseCode().null", CourseDAO.class);
        return Optional.empty();
    }
    @Override
    public Course update(Course entity) {
        Connection connection = null;
        try{
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COURSE)) {
                preparedStatement.setString(1, entity.getCourse_id());
                preparedStatement.setString(2, entity.getCourse_name());
                preparedStatement.setInt(3, entity.getCredits());
                preparedStatement.setInt(4, entity.getTeacher_id());
                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows == 0) {
                    throw new SQLException("CourseDAO.update(): Course could not be updated");
                }
            }
            connection.commit();
            System.out.println("CourseDAO.update(): Course updated with id: " + entity.getCourse_id());
            return entity;
        }catch (SQLException e){
            if(connection != null){
                try{
                    connection.rollback();
                }catch (Exception ex){
                    DatabaseUtils.handleSQLException("CourseDAO.update().rollback", e, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("CourseDAO.update().null", e, LOGGER);
            return null;
        }finally{
            if(connection != null) {
                try{
                    DatabaseUtils.printSQLConnectionClose("CourseDAO.update()", CourseDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                }catch (SQLException ez){
                    DatabaseUtils.handleSQLException("CourseDAO.update().finally.close()", ez, LOGGER);
                }
            }
        }
    }

    public boolean deleteCourseById(String courseId){
        Connection connection = null;
        try{
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COURSE_ID)){
                preparedStatement.setString(1, courseId);
                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows == 0) {
                    throw new SQLException("CourseDAO.delete(): Course could not be deleted");
                }
            }
            connection.commit();
            DatabaseUtils.printSQLConnectionClose("CourseDAO.delete()", CourseDAO.class);
            return true;
        }catch (SQLException e){
            if(connection != null){
                try{
                    connection.rollback();
                }catch (SQLException ex){
                    DatabaseUtils.handleSQLException("CourseDAO.delete().rollback", e, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("CourseDAO.delete().null", e, LOGGER);
            return false;
        }finally{
            if(connection != null){
                try{
                    DatabaseUtils.printSQLConnectionClose("CourseDAO.delete()", CourseDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                }catch (SQLException ez){
                    DatabaseUtils.handleSQLException("CourseDAO.delete().finally.close()", ez, LOGGER);
                }
            }
        }
    }

    private List<Course> processResultSet(ResultSet resultSet) throws SQLException {
        List<Course> courses = new ArrayList<>();
        while(resultSet.next()) {
            Course course = new Course();
            course.setCourse_id(resultSet.getString("course_id"));
            course.setCourse_name(resultSet.getString("course_name"));
            course.setCredits(resultSet.getInt("credits"));
            course.setCreated_at(DateUtils.toLocalDateTime(resultSet.getTimestamp("user_created_at")));
            courses.add(course);
        }
        return courses;
    }

    @Override
    public Optional<Course> getOne(Integer courseId) {
        return null;
    }
    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}
