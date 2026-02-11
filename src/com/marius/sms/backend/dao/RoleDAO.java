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
    private static final String SQL_GET_ALL_ROLES_QUERY = "SELECT * FROM role";
    private static final String SQL_GET_ROLE_BY_ID_QUERY = "SELECT * FROM role WHERE id = ?";
    private static final String SQL_INSERT_ROLE = "INSERT INTO role (name, description) VALUES (?, ?)";
    private static final String SQL_DELETE_ROLE = "DELETE FROM role WHERE id = ?";
    private static final String SQL_UPDATE_ROLE = "UPDATE role SET name = ? WHERE id = ?";


    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        Connection connection = DatabaseUtils.getConnection();

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_ROLES_QUERY);
            roles = processResultSet(resultSet);
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("RoleDAO.getAll()", e, LOGGER);
        }
        return roles;
    }

    @Override
    public Role create(Role entity) {
        Connection connection = DatabaseUtils.getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ROLE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, entity.getRole_id());
            preparedStatement.setString(2, entity.getRole_name());
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("RoleDAO.create().affectedRows: Failed to insert new role");
            }
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if(resultSet.next()){
                    int generatedId = resultSet.getInt(1);
                    entity.setRole_id(generatedId);
                }else{
                    throw new SQLException("RoleDAO.create().generatingKeys(): Failed to insert new role");
                }
            }
            connection.commit();
            preparedStatement.close();
            return entity;
        } catch (SQLException e) {
            try{
                connection.rollback();
            } catch (SQLException ex) {
                DatabaseUtils.handleSQLException("RoleDAO.create().rollbackException", e, LOGGER);
            }
        }
        return null;
    }

    @Override
    public Optional<Role> getOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Role update(Role entity) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

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

