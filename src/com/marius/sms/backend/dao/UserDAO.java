package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.User;
import com.marius.sms.util.DatabaseUtils;
import com.marius.sms.backend.security.PasswordChecker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserDAO implements DAO<User,Integer>{
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private static final String TABLE_NAME = "sms.users";

    private static final String SQL_GET_ALL_USERS_QUERY =
            "SELECT user_id, username, password_hash, role_id FROM " + TABLE_NAME;

    private static final String SQL_GET_USER_BY_ID_QUERY =
            "SELECT user_id, username, password_hash, role_id FROM " + TABLE_NAME + " WHERE user_id = ?";

    private static final String SQL_INSERT_USER =
            "INSERT INTO " + TABLE_NAME + " (username, password_hash, role_id) VALUES (?, ?, ?)";

    private static final String SQL_UPDATE_USER =
            "UPDATE " + TABLE_NAME + " SET username = ?, password_hash = ?, role_id = ? WHERE user_id = ?";

    private static final String SQL_DELETE_USER =
            "DELETE FROM " + TABLE_NAME + " WHERE user_id = ?";

    private static final String SQL_GET_USER_LOGIN_INFO_QUERY =
            "SELECT user_id, username, password_hash, role_id FROM " + TABLE_NAME + " WHERE username = ?";



    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseUtils.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_GET_ALL_USERS_QUERY)) {

            users = processResultSet(rs);
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("UserDAO.getAll()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("UserDAO.getAll()", UserDAO.class);
        return users;
    }

    @Override
    public User create(User entity) {
        Connection connection = null;
        try {
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, entity.getUsername());
                ps.setString(2, PasswordChecker.hashPassword(entity.getPassword_hash()));
                ps.setInt(3, entity.getRole_id());

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("UserDAO.create(): Failed to insert user.");
                }

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        entity.setUser_id(keys.getInt(1));
                    }
                }

                connection.commit();
                DatabaseUtils.printNewEntityCreated(entity, entity.getUser_id());
                return entity;
            }

        } catch (SQLException e) {
            if (connection != null) {
                try { connection.rollback(); } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("UserDAO.create().rollback", ex, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("UserDAO.create().null", e, LOGGER);
            return null;
        } finally {
            if (connection != null) {
                try {
                    DatabaseUtils.printSQLConnectionClose("UserDAO.create()", UserDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("UserDAO.create().finally.close", ex, LOGGER);
                }
            }
        }
    }


    @Override
    public Optional<User> getOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    private List<User> processResultSet(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setUser_id(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword_hash(rs.getString("password_hash"));
            user.setRole_id(rs.getInt("role_id"));
            users.add(user);
        }
        return users;
    }
}
