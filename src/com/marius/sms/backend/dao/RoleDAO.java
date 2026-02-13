package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.Role;
import com.marius.sms.util.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class RoleDAO implements DAO<Role, Integer> {
    private static final Logger LOGGER = Logger.getLogger(RoleDAO.class.getName());
    private static final String TABLE_NAME = "sms.roles";

    private static final String SQL_GET_ALL_ROLES_QUERY = "SELECT role_id, role_name FROM "+TABLE_NAME;
    private static final String SQL_GET_ROLE_BY_ID_QUERY = "SELECT role_id, role_name FROM "+TABLE_NAME+" WHERE role_id = ?";
    private static final String SQL_INSERT_ROLE = "INSERT INTO "+TABLE_NAME+" (role_name) VALUES (?)";
    private static final String SQL_DELETE_ROLE = "DELETE FROM "+TABLE_NAME+" WHERE role_id = ?";
    private static final String SQL_UPDATE_ROLE = "UPDATE "+TABLE_NAME+" SET role_name = ? WHERE role_id = ?";


    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try(
                Connection connection = DatabaseUtils.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_ROLES_QUERY);
                ){

            roles = processResultSet(resultSet);
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("RoleDAO.getAll()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("RoleDAO.getAll()", RoleDAO.class);
        return roles;
    }

    @Override
    public Role create(Role entity) {
        Connection connection = null;
        try {
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ROLE)) {

                preparedStatement.setString(1, entity.getRole_name());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Failed to insert new role.");
                }
                connection.commit();
                DatabaseUtils.printNewEntityCreated(entity, entity.getRole_id());
                return entity;
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("RoleDAO.rollback()", ex, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("RoleDAO.create().null", e, LOGGER);
            return null;

        } finally {
            if (connection != null) {
                try {
                    DatabaseUtils.printSQLConnectionClose("RoleDAO.create()", RoleDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ez) {
                    DatabaseUtils.handleSQLException("RoleDAO.create().close()", ez, LOGGER);
                }
            }
        }
    }


    @Override
    public Optional<Role> getOne(Integer roleId) {

        try(
                Connection connection = DatabaseUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ROLE_BY_ID_QUERY);) {
            preparedStatement.setInt(1, roleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Role> roles = processResultSet(resultSet);
            if(!roles.isEmpty()){
                System.out.println("Role found with id " + roleId);
                DatabaseUtils.printSQLConnectionClose("RoleDAO.getOne()", RoleDAO.class);
                return Optional.of(roles.get(0));
            }
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("RoleDAO.getOne()", e, LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("RoleDAO.getOne().null", RoleDAO.class);
        return Optional.empty();
    }

    @Override
    public Role update(Role entity) {
        Connection connection = null;
        try {
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_ROLE)) {
                ps.setString(1, entity.getRole_name());
                ps.setInt(2, entity.getRole_id());

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("RoleDAO.update(): Role not found with id " + entity.getRole_id());
                }
            }
            connection.commit();
            System.out.println("Role updated with id "+ entity.getRole_id());
            return entity;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("RoleDAO.update().rollback", ex, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("RoleDAO.update()", e, LOGGER);
            return null;
        } finally {
            if (connection != null) {
                try {
                    DatabaseUtils.printSQLConnectionClose("RoleDAO.update()", RoleDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ignored) {}
            }
        }
    }

    @Override
    public boolean delete(Integer integer) {
        Connection connection = null;
        try{
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ROLE)) {
                preparedStatement.setInt(1, integer);
                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows == 0){
                    throw new SQLException("RoleDAO.delete().affectedRows: Failed to insert new role");
                }
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("RoleDAO.delete().rollback", e, LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("RoleDAO.delete().null", e, LOGGER);
            return false;
        } finally {
            if (connection != null) {
                try {
                    DatabaseUtils.printSQLConnectionClose("RoleDAO.delete()", RoleDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    DatabaseUtils.handleSQLException("RoleDAO.delete().finally.close", e, LOGGER);
                }
            }
        }
    }
    private List<Role> processResultSet(ResultSet rs) throws SQLException {
        List<Role> roles = new ArrayList<>();
        while (rs.next()) {
            Role role = new Role();
            role.setRole_id(rs.getInt("role_id"));
            role.setRole_name(rs.getString("role_name"));
            roles.add(role);
        }
        return roles;
    }
}

