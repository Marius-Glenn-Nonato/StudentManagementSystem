package com.marius.sms.backend.dao;

import com.marius.sms.backend.entities.Course;

import java.util.List;
import java.util.Optional;

public class CourseDAO implements DAO<Course, Integer> {
    @Override
    public List<Course> getAll() {
        return List.of();
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
}
