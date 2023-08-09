package com.hungryhero.model;

public class AuthenticationResult {
    private boolean authenticated;
    private Long userId;

    public AuthenticationResult(boolean authenticated, Long userId) {
        this.authenticated = authenticated;
        this.userId = userId;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public Long getUserId() {
        return userId;
    }
}
