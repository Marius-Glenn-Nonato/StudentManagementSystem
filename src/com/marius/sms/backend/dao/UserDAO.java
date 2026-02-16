package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.User;
import com.marius.sms.util.DatabaseUtils;
import com.marius.sms.backend.security.PasswordChecker;
import com.marius.sms.util.DateUtils;

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

    private static final String SQL_GET_USER_LOGIN_INFO_BY_USERNAME_QUERY =
            "SELECT user_id, username, password_hash, email , role_id, created_at FROM " + TABLE_NAME + " WHERE username = ?";

    private static final String SQL_GET_USER_LOGIN_INFO_BY_EMAIL_QUERY = 
            "SELECT user_id, username, password_hash,email, role_id, created_at FROM " + TABLE_NAME + " WHERE email = ?";

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = DatabaseUtils.getConnection();
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
    public Optional<User> getOne(Integer userId) {
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_GET_USER_BY_ID_QUERY)
            ) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<User> users = processResultSet(rs);
            if (!users.isEmpty()) {
                DatabaseUtils.printSQLConnectionClose("UserDAO.getOne()", UserDAO.class);
                return Optional.of(users.get(0));
            }

        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("UserDAO.getOne()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("UserDAO.getOne().null", UserDAO.class);
        return Optional.empty();
    }

    /** Get login info by username for authentication */
    public Optional<User> getUserLoginUsingUserName(String username) {
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_GET_USER_LOGIN_INFO_BY_USERNAME_QUERY)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            List<User> users = processResultSet(rs);
            if (!users.isEmpty()) {
                DatabaseUtils.printSQLConnectionClose("UserDAO.getUserLoginUsingUserName()", UserDAO.class);
                return Optional.of(users.get(0));
            }

        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("UserDAO.getUserLoginUsingUserName()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("UserDAO.getUserLoginUsingUserName().null", UserDAO.class);
        return Optional.empty();
    }
    
    /** Get login info by user_email for authentication */
    public Optional<User> getUserLoginUsingEmail(String email) {
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_GET_USER_LOGIN_INFO_BY_EMAIL_QUERY)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            List<User> users = processResultSet(rs);
            if (!users.isEmpty()) {
                DatabaseUtils.printSQLConnectionClose("UserDAO.getUserLoginUsingEmail()", UserDAO.class);
                return Optional.of(users.get(0));
            }

        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("UserDAO.getUserLoginUsingEmail()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("UserDAO.getUserLoginUsingEmail().null", UserDAO.class);
        return Optional.empty();
    }

    @Override
    public User update(User entity) {
        Connection connection = null;
        try {
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_USER)) {
                ps.setString(1, entity.getUsername());
                ps.setString(2, entity.getPassword_hash());
                ps.setInt(3, entity.getRole_id());
                ps.setInt(4, entity.getUser_id());

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("UserDAO.update(): User not found with id " + entity.getUser_id());
                }
            }
            connection.commit();
            DatabaseUtils.printNewEntityCreated(entity, entity.getUser_id());
            return entity;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("UserDAO.update().rollback", ex, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("UserDAO.update()", e, LOGGER);
            return null;
        } finally {
            if (connection != null) {
                try {
                    DatabaseUtils.printSQLConnectionClose("UserDAO.update()", UserDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("UserDAO.update().finally.close", ex, LOGGER);
                }
            }
        }
    }

    @Override
    public boolean delete(Integer userId) {
        Connection connection = null;
        try {
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_USER)) {
                ps.setInt(1, userId);

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("UserDAO.delete(): User not found with id " + userId);
                }
            }
            connection.commit();
            DatabaseUtils.printSQLConnectionClose("UserDAO.delete()", UserDAO.class);
            return true;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("UserDAO.delete().rollback", ex, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("UserDAO.delete()", e, LOGGER);
            return false;
        } finally {
            if (connection != null) {
                try {
                    DatabaseUtils.printSQLConnectionClose("UserDAO.delete().finally", UserDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("UserDAO.delete().finally.close", ex, LOGGER);
                }
            }
        }
    
    }

     private List<User> processResultSet(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setUser_email(rs.getString("user_email"));
                user.setPassword_hash(rs.getString("password_hash"));
                user.setRole_id(rs.getInt("role_id"));
                user.setUser_created_at(DateUtils.toLocalDateTime(rs.getTimestamp("user_created_at")));
                users.add(user);
            }
        }catch(SQLException e) {
            DatabaseUtils.handleSQLException("UserDAO.processResultSet()", e, LOGGER);
        }
        return users;
    }
}
