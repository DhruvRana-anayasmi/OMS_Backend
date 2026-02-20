package com.example.OMS.dto;

public class RegisterResponseDto {
    private String username;
    private Long userId;

    public RegisterResponseDto(String username, Long userId) {
        this.username=username;
        this.userId=userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
