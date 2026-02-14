package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.AuditLog;
import com.marius.sms.util.DatabaseUtils;
import com.marius.sms.util.DateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class AuditLogDAO implements DAO<AuditLog,Integer> {
    private static final Logger LOGGER = Logger.getLogger(AuditLogDAO.class.getName());

    private static final String TABLE_NAME = "sms.audit_logs";
    private static final String SQL_GET_ALL_AUDIT_LOGS_QUERY = "SELECT audit_id, user_id, action, entity_type, entity_id, created_at FROM " +TABLE_NAME;

    private static final String SQL_GET_AUDIT_LOG_BY_ID_QUERY = "SELECT audit_id, user_id, action, entity_type, entity_id, created_at FROM " +TABLE_NAME +" WHERE audit_id = ?";

    private static final String SQL_INSERT_AUDIT_LOG_QUERY = "INSERT INTO "+TABLE_NAME+ " (user_id, action, entity_type, entity_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_AUDIT_LOG_QUERY = "UPDATE "+TABLE_NAME+" SET action=?, entity_type=?, entity_id=? WHERE audit_id=?";
    private static final String SQL_DELETE_AUDIT_LOG_QUERY = "DELETE FROM "+TABLE_NAME+" WHERE audit_id=?";

    @Override
    public List<AuditLog> getAll() {
        List<AuditLog> auditLogs = new ArrayList<>();
        try(
                Connection connection = DatabaseUtils.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_AUDIT_LOGS_QUERY);
                ){
            auditLogs = processResultSet(resultSet);

        }catch (SQLException e){
            DatabaseUtils.handleSQLException("AuditLogDAO.getAll().getAll()",e,LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("AuditLogDAO.getAll()", AuditLogDAO.class);
        return auditLogs;
    }

    @Override
    public AuditLog create(AuditLog entity) {
        Connection connection = null;
        try{
            connection = DatabaseUtils.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_AUDIT_LOG_QUERY, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setInt(1, entity.getUser_id());
                preparedStatement.setString(2, entity.getAction());
                preparedStatement.setString(3, entity.getEntity_type());
                preparedStatement.setInt(4, entity.getEntity_id());
                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows == 0) {
                    throw new SQLException("Failed to add audit");
                }
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    if(generatedKeys.next()) {
                        entity.setAudit_id(generatedKeys.getInt(1));
                    }
                }
                connection.commit();
                DatabaseUtils.printNewEntityCreated(entity, entity.getAudit_id());
                return entity;
            }
        }catch (SQLException e){
            if(connection != null){
                try{
                    connection.rollback();
                } catch (SQLException ex) {
                    DatabaseUtils.handleSQLException("StudentDAO.create().rollback",ex,LOGGER);
                }
            }
            DatabaseUtils.handleSQLException("StudentDAO.create().null",e,LOGGER);
            return null;
        }finally{
            if(connection != null){
                try{
                    DatabaseUtils.printSQLConnectionClose("AuditLogDAO.create().close", AuditLogDAO.class);
                    connection.setAutoCommit(true);
                    connection.close();
                } catch(SQLException ez){
                    DatabaseUtils.handleSQLException("AuditLogDAO.create().finally.close()",ez,LOGGER);
                }
            }
        }
    }

    @Override
    public Optional<AuditLog> getOne(Integer auditId) {
        try(
                Connection connection = DatabaseUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_AUDIT_LOG_BY_ID_QUERY);
                ){
            preparedStatement.setInt(1,auditId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<AuditLog> auditLogs = processResultSet(resultSet);
            if(auditLogs.isEmpty()){
                System.out.println("Audit Log found with id: " + auditId);
                DatabaseUtils.printSQLConnectionClose("AuditLogDAO.getOne()",AuditLogDAO.class);
                return Optional.of(auditLogs.get(0));
            }
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("AuditLogDAO.getOne()",e,LOGGER);
        }
        DatabaseUtils.printSQLConnectionClose("AuditLogDAO.getOne()",AuditLogDAO.class);
        return Optional.empty();
    }

    private List<AuditLog> processResultSet(ResultSet resultSet) throws SQLException {
        List<AuditLog> auditLogs = new ArrayList<>();
        while(resultSet.next()){
            AuditLog auditLog = new AuditLog();
            auditLog.setAudit_id(resultSet.getInt("audit_id"));
            auditLog.setUser_id(resultSet.getInt("user_id"));
            auditLog.setAction(resultSet.getString("action"));
            auditLog.setEntity_type(resultSet.getString("entity_type"));
            auditLog.setEntity_id(resultSet.getInt("entity_id"));
            auditLog.setCreated_at(DateUtils.toLocalDateTime(resultSet.getTimestamp("created_at")));
            auditLogs.add(auditLog);
        }
        return auditLogs;
    }

    @Override
    public AuditLog update(AuditLog entity) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}
