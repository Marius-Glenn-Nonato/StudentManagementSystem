package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.Teacher;
import com.marius.sms.util.DatabaseUtils;
import com.marius.sms.util.DateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TeacherDAO implements DAO<Teacher, Integer> {
    private static final Logger LOGGER = Logger.getLogger(TeacherDAO.class.getName());
    private static final String TABLE_NAME = "sms.teachers";

    private static final String SQL_GET_ALL_TEACHERS_QUERY = "SELECT t.*, u.username, u.email, u.password_hash, u.role_id, u.created_at FROM " + TABLE_NAME + " t JOIN sms.users u ON t.user_id = u.user_id";
    private static final String SQL_GET_TEACHER_BY_ID_QUERY = "SELECT t.*, u.username, u.email, u.password_hash, u.role_id, u.created_at FROM " + TABLE_NAME + " t JOIN sms.users u ON t.user_id = u.user_id WHERE t.teacher_id = ?";
    private static final String SQL_GET_TEACHER_BY_USER_ID_QUERY = "SELECT t.*, u.username, u.email, u.password_hash, u.role_id, u.created_at FROM " + TABLE_NAME + " t JOIN sms.users u ON t.user_id = u.user_id WHERE t.user_id = ?";
    private static final String SQL_GET_TEACHER_BY_LAST_NAME_QUERY = "SELECT t.*, u.username, u.email, u.password_hash, u.role_id, u.created_at FROM " + TABLE_NAME + " t JOIN sms.users u ON t.user_id = u.user_id WHERE t.last_name = ?";
    private static final String SQL_GET_TEACHER_BY_FIRST_AND_LAST_NAME_QUERY = "SELECT t.*, u.username, u.email, u.password_hash, u.role_id, u.created_at FROM " + TABLE_NAME + " t JOIN sms.users u ON t.user_id = u.user_id WHERE t.first_name = ? AND t.last_name = ?";
    private static final String SQL_GET_TEACHER_BY_EMAIL_QUERY = "SELECT t.*, u.username, u.email, u.password_hash, u.role_id, u.created_at FROM " + TABLE_NAME + " t JOIN sms.users u ON t.user_id = u.user_id WHERE u.email = ?";

    private static final String SQL_INSERT_TEACHER_QUERY = "INSERT INTO " + TABLE_NAME + " (user_id, first_name, last_name, hire_date) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE_TEACHER_QUERY = "UPDATE " + TABLE_NAME + " SET first_name=?, last_name=?, hire_date=? WHERE teacher_id=?";
    private static final String SQL_DELETE_TEACHER_QUERY = "DELETE FROM " + TABLE_NAME + " WHERE teacher_id=?";

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList<>();
        try (
                Connection connection = DatabaseUtils.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_TEACHERS_QUERY);
        ) {
            teachers = processResultSet(resultSet);
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("TeacherDAO.getAll()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("TeacherDAO.getAll()", TeacherDAO.class);
        return teachers;
    }

    @Override
    public Teacher create(Teacher entity) {
        Connection connection = null;
        try {
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_TEACHER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, entity.getUser_id());
                ps.setString(2, entity.getFirst_name());
                ps.setString(3, entity.getLast_name());
                ps.setDate(4, DateUtils.toSqlDate(entity.getHire_date()));

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("TeacherDAO.create(): Failed to insert teacher.");
                }

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        entity.setTeacher_id(keys.getInt(1));
                    }
                }

                connection.commit();
                DatabaseUtils.printNewEntityCreated(entity, entity.getTeacher_id());
                return entity;
            }

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("TeacherDAO.create().rollback", ex, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("TeacherDAO.create().null", e, LOGGER);
            return null;
        } finally {
            if (connection != null) {
                try {
                    DatabaseUtils.printSQLConnectionClose("TeacherDAO.create()", TeacherDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("TeacherDAO.create().finally.close", ex, LOGGER);
                }
            }
        }
    }

    @Override
    public Optional<Teacher> getOne(Integer teacherId) {
        try (
                Connection connection = DatabaseUtils.getConnection();
                PreparedStatement ps = connection.prepareStatement(SQL_GET_TEACHER_BY_ID_QUERY);
        ) {
            ps.setInt(1, teacherId);
            ResultSet resultSet = ps.executeQuery();
            List<Teacher> teachers = processResultSet(resultSet);
            if (!teachers.isEmpty()) {
                System.out.println("Teacher found with id " + teacherId);
                DatabaseUtils.printSQLConnectionClose("TeacherDAO.getOne()", TeacherDAO.class);
                return Optional.of(teachers.get(0));
            }
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("TeacherDAO.getOne()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("TeacherDAO.getOne().null", TeacherDAO.class);
        return Optional.empty();
    }

    //TEACHER GET BY USER ID
    public Optional<Teacher> getTeacherByUserId(Integer userId) {
        try (
                Connection connection = DatabaseUtils.getConnection();
                PreparedStatement ps = connection.prepareStatement(SQL_GET_TEACHER_BY_USER_ID_QUERY);
        ) {
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            List<Teacher> teachers = processResultSet(resultSet);
            if (!teachers.isEmpty()) {
                System.out.println("Teacher found with user id " + userId);
                DatabaseUtils.printSQLConnectionClose("TeacherDAO.getTeacherByUserId()", TeacherDAO.class);
                return Optional.of(teachers.get(0));
            }
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("TeacherDAO.getTeacherByUserId()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("TeacherDAO.getTeacherByUserId().null", TeacherDAO.class);
        return Optional.empty();
    }

    @Override
    public Teacher update(Teacher entity) {
        Connection connection = null;
        try {
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_TEACHER_QUERY)) {
                ps.setString(1, entity.getFirst_name());
                ps.setString(2, entity.getLast_name());
                ps.setDate(3, DateUtils.toSqlDate(entity.getHire_date()));
                ps.setInt(4, entity.getTeacher_id());

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("TeacherDAO.update(): Teacher not found with id " + entity.getTeacher_id());
                }
            }
            connection.commit();
            System.out.println("Teacher updated with id " + entity.getTeacher_id());
            return entity;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("TeacherDAO.update().rollback", ex, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("TeacherDAO.update()", e, LOGGER);
            return null;
        } finally {
            if (connection != null) {
                try {
                    DatabaseUtils.printSQLConnectionClose("TeacherDAO.update()", TeacherDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("TeacherDAO.update().finally.close", ex, LOGGER);
                }
            }
        }
    }

    @Override
    public boolean delete(Integer teacherId) {
        Connection connection = null;
        try {
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_TEACHER_QUERY)) {
                ps.setInt(1, teacherId);

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("TeacherDAO.delete(): Teacher not found with id " + teacherId);
                }
            }
            connection.commit();
            DatabaseUtils.printSQLConnectionClose("TeacherDAO.delete()", TeacherDAO.class);
            return true;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("TeacherDAO.delete().rollback", ex, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("TeacherDAO.delete()", e, LOGGER);
            return false;
        } finally {
            if (connection != null) {
                try {
                    DatabaseUtils.printSQLConnectionClose("TeacherDAO.delete().finally", TeacherDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("TeacherDAO.delete().finally.close", ex, LOGGER);
                }
            }
        }
    }

    private List<Teacher> processResultSet(ResultSet rs) throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        while (rs.next()) {
            Teacher teacher = new Teacher();
            // User data
            teacher.setUser_id(rs.getInt("user_id"));
            teacher.setUsername(rs.getString("username"));
            teacher.setEmail(rs.getString("email"));
            teacher.setPassword_hash(rs.getString("password_hash"));
            teacher.setRole_id(rs.getInt("role_id"));
            teacher.setCreated_at(DateUtils.toLocalDateTime(rs.getTimestamp("created_at")));
            // Teacher data
            teacher.setTeacher_id(rs.getInt("teacher_id"));
            teacher.setFirst_name(rs.getString("first_name"));
            teacher.setLast_name(rs.getString("last_name"));
            teacher.setHire_date(DateUtils.toLocalDate(rs.getDate("hire_date")));
            teachers.add(teacher);
        }
        return teachers;
    }
}
