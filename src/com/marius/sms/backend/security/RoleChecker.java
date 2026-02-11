package com.marius.sms.backend.security;

import com.marius.sms.backend.entities.User;

public class RoleChecker {

    public static boolean isAdmin(User user) {
        return user.getRole_id() == 1;
    }

    public static boolean isStudent(User user) {
        return user.getRole_id() == 2;
    }

    public static boolean isRegistrar(User user) {
        return user.getRole_id() == 3;
    }
}
