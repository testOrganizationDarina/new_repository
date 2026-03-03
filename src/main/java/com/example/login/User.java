package com.example.login;

/**
 * Represents an application user with a username and hashed password.
 */
public class User {

    private final String username;
    private final String hashedPassword;

    public User(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Checks whether the supplied hashed password matches the stored one.
     *
     * @param hashedPassword the hashed password to verify
     * @return {@code true} if the passwords match
     */
    public boolean verifyPassword(String hashedPassword) {
        return this.hashedPassword.equals(hashedPassword);
    }
}
