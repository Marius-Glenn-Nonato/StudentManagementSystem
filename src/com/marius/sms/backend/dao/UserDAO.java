package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserDAO implements DAO<User,Integer>{
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    private static final String TABLE_NAME = "sms.users";

    private static final String SQL_GET_ALL_USERS_QUERY = "SELECT * FROM "+TABLE_NAME;



    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User create(User entity) {
        return null;
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
}
