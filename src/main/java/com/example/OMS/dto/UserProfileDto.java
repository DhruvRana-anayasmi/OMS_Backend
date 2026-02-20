package com.example.OMS.dto;

import java.util.List;
public record UserProfileDto(
        Long userId,
        String username,
        List<String> roles
) {}
