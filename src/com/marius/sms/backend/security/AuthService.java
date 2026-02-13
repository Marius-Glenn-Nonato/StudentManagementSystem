package com.marius.sms.backend.security;

import com.marius.sms.backend.dao.UserDAO;
import com.marius.sms.backend.entities.User;
import com.marius.sms.backend.exception.AuthenticationException;
import java.util.Optional;
import java.util.logging.Logger;

public class AuthService {
    private static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());
    private final UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Authenticate user by username and password
     * @param username the user's username
     * @param password the user's password (plain text)
     * @return the authenticated User
     * @throws AuthenticationException if credentials are invalid
     */
    public User loginWithUsername(String username, String password) throws AuthenticationException {
        Optional<User> userOpt = userDAO.getUserLoginUsingUserName(username);

        if (!userOpt.isPresent()) {
            LOGGER.warning("Login attempt failed: User not found with username " + username);
            throw new AuthenticationException("Invalid username or password");
        }

        User user = userOpt.get();
        if (!PasswordChecker.verifyPassword(password, user.getPassword_hash())) {
            LOGGER.warning("Login attempt failed: Invalid password for user " + username);
            throw new AuthenticationException("Invalid username or password");
        }

        LOGGER.info("User " + username + " logged in successfully");
        return user;
    }

    /**
     * Authenticate user by email and password
     * @param email the user's email
     * @param password the user's password (plain text)
     * @return the authenticated User
     * @throws AuthenticationException if credentials are invalid
     */
    public User loginWithEmail(String email, String password) throws AuthenticationException {
        Optional<User> userOpt = userDAO.getUserLoginUsingEmail(email);

        if (!userOpt.isPresent()) {
            LOGGER.warning("Login attempt failed: User not found with email " + email);
            throw new AuthenticationException("Invalid email or password");
        }

        User user = userOpt.get();
        if (!PasswordChecker.verifyPassword(password, user.getPassword_hash())) {
            LOGGER.warning("Login attempt failed: Invalid password for email " + email);
            throw new AuthenticationException("Invalid email or password");
        }

        LOGGER.info("User with email " + email + " logged in successfully");
        return user;
    }

    /**
     * Authenticate user by username or email and password
     * @param usernameOrEmail the user's username or email
     * @param password the user's password (plain text)
     * @return the authenticated User
     * @throws AuthenticationException if credentials are invalid
     */
    public User login(String usernameOrEmail, String password) throws AuthenticationException {
        // Try username first
        Optional<User> userOpt = userDAO.getUserLoginUsingUserName(usernameOrEmail);
        
        // If not found by username, try email
        if (!userOpt.isPresent()) {
            userOpt = userDAO.getUserLoginUsingEmail(usernameOrEmail);
        }

        if (!userOpt.isPresent()) {
            LOGGER.warning("Login attempt failed: User not found with username or email " + usernameOrEmail);
            throw new AuthenticationException("Invalid credentials");
        }

        User user = userOpt.get();
        if (!PasswordChecker.verifyPassword(password, user.getPassword_hash())) {
            LOGGER.warning("Login attempt failed: Invalid password for " + usernameOrEmail);
            throw new AuthenticationException("Invalid credentials");
        }

        LOGGER.info("User " + usernameOrEmail + " logged in successfully");
        return user;
    }
}
