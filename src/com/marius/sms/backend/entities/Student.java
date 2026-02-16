package com.marius.sms.backend.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student extends User {
    private Integer student_id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private LocalDate date_of_birth;
    private Integer program_id;
    private Integer curriculum_id;
    private Integer year_level;
    private boolean is_irregular;
    private boolean is_active;
    private LocalDate graduated_at;



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

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public Integer getProgram_id() {
        return program_id;
    }

    public void setProgram_id(Integer program_id) {
        this.program_id = program_id;
    }

    public Integer getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(Integer curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public Integer getYear_level() {
        return year_level;
    }

    public void setYear_level(Integer year_level) {
        this.year_level = year_level;
    }

    public boolean isIs_irregular() {
        return is_irregular;
    }

    public void setIs_irregular(boolean is_irregular) {
        this.is_irregular = is_irregular;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public LocalDate getGraduated_at() {
        return graduated_at;
    }

    public void setGraduated_at(LocalDate graduated_at) {
        this.graduated_at = graduated_at;
    }

    @Override
    public String toString() {
        return "Student{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", user_email='" + user_email + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", role_id=" + role_id +
                ", student_id=" + student_id +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", user_created_at=" + user_created_at +
                ", program_id='" + program_id + '\'' +
                ", curriculum_id='" + curriculum_id + '\'' +
                ", year_level='" + year_level + '\'' +
                ", is_irregular=" + is_irregular +
                ", is_active=" + is_active +
                ", graduated_at=" + graduated_at +
                '}';
    }
}
