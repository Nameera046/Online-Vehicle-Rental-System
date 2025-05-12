package com.vehicle.vechiclemanagement.dto;

public class LoginResponse {
    private String token;
    private String role;
    private String redirectUrl;
    private Long userId; // Add userId field

    public LoginResponse(String token, String role, String redirectUrl, Long userId) {
        this.token = token;
        this.role = role;
        this.redirectUrl = redirectUrl;
        this.userId = userId;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getRedirectUrl() { return redirectUrl; }
    public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}