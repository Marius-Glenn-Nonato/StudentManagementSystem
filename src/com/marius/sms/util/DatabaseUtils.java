package com.marius.sms.util;

import com.marius.sms.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseUtils {
    private static final Logger LOGGER = Logger.getLogger(DatabaseUtils.class.getName());
    private static final DatabaseConfig DATABASE_CONFIG = new DatabaseConfig();
    private static final String exceptionFormat = "Exception in %s, message: %s, code: %s;";


    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(DATABASE_CONFIG.getUrl(), DATABASE_CONFIG.getUsername(), DATABASE_CONFIG.getPassword());
        }catch(SQLException e){
            handleSQLException("util.DatabaseUtils.getConnection", e, LOGGER);
            return null;
        }
    }

    public static void handleSQLException(String methodName, SQLException e, Logger log) {
        log.warning(String.format(exceptionFormat, methodName, e.getMessage(),e.getErrorCode()));
        throw new RuntimeException(e);
    }

    public static void printSQLConnectionClose(String methodName, Class classname) {
        System.out.println("Connection closed " +methodName+" @"+ classname.getName());
    }

    public static void printNewEntityCreated(Object entity, int id) {
        System.out.println("New " + entity.getClass().getName() + " created with id " + id);
    }

    public static void printNewEntityCreated(Object entity, String id) {
        System.out.println("New " + entity.getClass().getName() + " created with id " + id);
    }
}
