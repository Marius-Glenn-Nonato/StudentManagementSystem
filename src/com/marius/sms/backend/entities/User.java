package com.marius.sms.backend.entities;


import java.time.LocalDateTime;

public class User {
    protected Integer user_id;
    protected String username;
    protected String user_email;
    protected String password_hash;
    protected Integer role_id;
    protected LocalDateTime user_created_at;

    public User() { }

    public User(int user_id, String username, String user_email, String password_hash,
                int role_id, LocalDateTime user_created_at) {
        this.user_id = user_id;
        this.username = username;
        this.user_email = user_email;
        this.password_hash = password_hash;
        this.role_id = role_id;
        this.user_created_at = user_created_at;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    public Integer getUser_id() { return user_id; }
    public void setUser_id(Integer user_id) { this.user_id = user_id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword_hash() { return password_hash; }
    public void setPassword_hash(String password_hash) { this.password_hash = password_hash; }

    public Integer getRole_id() { return role_id; }
    public void setRole_id(Integer role_id) { this.role_id = role_id; }


    public LocalDateTime getUser_created_at() { return user_created_at; }
    public void setUser_created_at(LocalDateTime user_created_at) { this.user_created_at = user_created_at; }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", user_email='" + user_email + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", role_id=" + role_id +
                ", user_created_at=" + user_created_at +
                '}';
    }
}
