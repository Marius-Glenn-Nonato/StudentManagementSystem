package com.marius.sms.backend.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Student {
    private Integer student_id;
    private String student_number;
    private String first_name;
    private String last_name;
    private String email;
    private Date date_of_birth;
    private Timestamp created_at;

    public Student() { }

    public Student(Integer student_id, String student_number, String first_name, String last_name, String email, Date date_of_birth, Timestamp created_at) {
        this.student_id = student_id;
        this.student_number = student_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.created_at = created_at;
    }

    public Integer getStudent_id() {
        return student_id;
    }
    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getStudent_number() {
        return student_number;
    }
    public void setStudent_number(String student_number) {
        this.student_number = student_number;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", student_number='" + student_number + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", created_at=" + created_at +
                '}';
    }
}
