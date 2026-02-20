package com.example.OMS.dto;

import com.example.OMS.entity.Role;
import com.example.OMS.entity.User_Role;

public class UserResponseDto {

    Long UserId;
    String userName;
    String role;

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
