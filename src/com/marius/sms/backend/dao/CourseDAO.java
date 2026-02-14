package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.Course;
import com.marius.sms.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CourseDAO implements DAO<Course, Integer> {
    private static final Logger LOGGER = Logger.getLogger(CourseDAO.class.getName());
    private static final String TABLE_NAME = "sms.courses";
    private static final String SQL_GET_ALL_COURSES = "SELECT * FROM " +TABLE_NAME;

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
        return null;
    }

    @Override
    public Optional<Course> getOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Course update(Course entity) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    private List<Course> processResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

}
