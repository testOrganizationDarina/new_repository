package com.example.login;

import java.util.Scanner;

/**
 * Entry point for the Java Login Application.
 * Provides a simple console-based login interface.
 */
public class LoginApp {

    public static void main(String[] args) {
        LoginService loginService = new LoginService();

        // Register a default demo user
        loginService.registerUser("admin", "admin123");
        loginService.registerUser("user", "password");

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Java Login Application ===");

        boolean loggedIn = false;
        int maxAttempts = 3;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();

            if (loginService.login(username, password)) {
                loggedIn = true;
                System.out.println("Login successful! Welcome, " + username + ".");
                break;
            } else {
                int remaining = maxAttempts - attempt;
                if (remaining > 0) {
                    System.out.println("Invalid credentials. " + remaining + " attempt(s) remaining.");
                } else {
                    System.out.println("Invalid credentials. No more attempts allowed.");
                }
            }
        }

        if (!loggedIn) {
            System.out.println("Access denied.");
        }

        scanner.close();
    }
}
