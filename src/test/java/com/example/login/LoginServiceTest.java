package com.example.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link LoginService}.
 */
class LoginServiceTest {

    private LoginService loginService;

    @BeforeEach
    void setUp() {
        loginService = new LoginService();
        loginService.registerUser("admin", "admin123");
        loginService.registerUser("user", "password");
    }

    @Test
    void loginSucceedsWithValidCredentials() {
        assertTrue(loginService.login("admin", "admin123"));
        assertTrue(loginService.login("user", "password"));
    }

    @Test
    void loginFailsWithWrongPassword() {
        assertFalse(loginService.login("admin", "wrongpassword"));
    }

    @Test
    void loginFailsWithUnknownUser() {
        assertFalse(loginService.login("unknown", "password"));
    }

    @Test
    void loginFailsWithNullCredentials() {
        assertFalse(loginService.login(null, "password"));
        assertFalse(loginService.login("admin", null));
        assertFalse(loginService.login(null, null));
    }

    @Test
    void registerUserThrowsOnDuplicateUsername() {
        assertThrows(IllegalArgumentException.class,
                () -> loginService.registerUser("admin", "anotherPassword"));
    }

    @Test
    void registerUserThrowsOnEmptyUsername() {
        assertThrows(IllegalArgumentException.class,
                () -> loginService.registerUser("", "password"));
    }

    @Test
    void registerUserThrowsOnEmptyPassword() {
        assertThrows(IllegalArgumentException.class,
                () -> loginService.registerUser("newuser", ""));
    }
}
