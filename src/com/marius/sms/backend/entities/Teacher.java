package com.marius.sms.backend.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Teacher extends User {
    private int teacher_id;
    private String first_name;
    private String last_name;
    private LocalDate hire_date;

    public Teacher(){
        super();
    }
    public Teacher(int user_id, String username, String email, String password_hash,
                   int role_id, LocalDateTime created_at, int teacher_id, String first_name,
                   String last_name, LocalDate hire_date) {
        super(user_id, username, email, password_hash, role_id, created_at);
        this.teacher_id = teacher_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hire_date = hire_date;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", hire_date=" + hire_date +
                '}';
    }
}
