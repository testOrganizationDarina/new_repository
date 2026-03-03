package com.example.login;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles user authentication by validating credentials against a registered user store.
 * Passwords are stored as SHA-256 hashes; plaintext passwords are never retained.
 */
public class LoginService {

    private final Map<String, User> userStore = new HashMap<>();

    /**
     * Registers a user with the given username and password.
     *
     * @param username the username to register
     * @param password the plaintext password to hash and store
     * @throws IllegalArgumentException if username or password is null or empty,
     *                                  or if the username is already registered
     */
    public void registerUser(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username must not be empty.");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password must not be empty.");
        }
        if (userStore.containsKey(username)) {
            throw new IllegalArgumentException("Username '" + username + "' is already registered.");
        }
        userStore.put(username, new User(username, hash(password)));
    }

    /**
     * Attempts to log in with the given credentials.
     *
     * @param username the username to authenticate
     * @param password the plaintext password to verify
     * @return {@code true} if credentials are valid, {@code false} otherwise
     */
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        User user = userStore.get(username);
        return user != null && user.verifyPassword(hash(password));
    }

    /**
     * Returns the SHA-256 hex digest of the given input.
     */
    static String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }
}
