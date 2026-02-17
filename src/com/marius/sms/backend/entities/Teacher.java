package com.marius.sms.backend.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Teacher extends User {
    private int teacher_id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private boolean isActive;
    private LocalDate leftAt;
    private LocalDateTime teacherCreatedAt;

    public Teacher(){
        super();
    }

    public Teacher(LocalDateTime teacherCreatedAt, LocalDate leftAt, boolean isActive, String last_name, String middle_name, String first_name, int teacher_id) {
        this.teacherCreatedAt = teacherCreatedAt;
        this.leftAt = leftAt;
        this.isActive = isActive;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.first_name = first_name;
        this.teacher_id = teacher_id;
    }

    public Teacher(int user_id, String username, String user_email, String password_hash, int role_id, LocalDateTime user_created_at, LocalDateTime teacherCreatedAt, LocalDate leftAt, boolean isActive, String last_name, String middle_name, String first_name, int teacher_id) {
        super(user_id, username, user_email, password_hash, role_id, user_created_at);
        this.teacherCreatedAt = teacherCreatedAt;
        this.leftAt = leftAt;
        this.isActive = isActive;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.first_name = first_name;
        this.teacher_id = teacher_id;
    }

    public LocalDate getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(LocalDate leftAt) {
        this.leftAt = leftAt;
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

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getTeacherCreatedAt() {
        return teacherCreatedAt;
    }

    public void setTeacherCreatedAt(LocalDateTime teacherCreatedAt) {
        this.teacherCreatedAt = teacherCreatedAt;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", isActive=" + isActive +
                ", leftAt=" + leftAt +
                ", teacherCreatedAt=" + teacherCreatedAt +
                ", user_id=" + user_id +
                ", username='" + username + '\'' +
                ", user_email='" + user_email + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", role_id=" + role_id +
                ", user_created_at=" + user_created_at +
                '}';
    }
}
