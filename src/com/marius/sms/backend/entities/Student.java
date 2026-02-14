package com.marius.sms.backend.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

public class Student extends User {
    private Integer student_id;
    private String first_name;
    private String last_name;
    private LocalDate date_of_birth;

    public Student() {
        super();
    }

    public Student(int user_id, String username, String email, String password_hash, int role_id,
                   LocalDateTime created_at, int student_id, LocalDate date_of_birth) {
        super(user_id, username, email, password_hash, role_id, created_at);
        this.student_id = student_id;
        this.date_of_birth = date_of_birth;
    }


    public Integer getStudent_id() {
        return student_id;
    }
    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }


    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }



    @Override
    public String toString() {
        return "Student{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", role_id=" + role_id +
                ", student_id=" + student_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", created_at=" + created_at +
                '}';
    }
}
