package com.example.OMS.mapper;

import com.example.OMS.dto.UserResponseDto;
import com.example.OMS.entity.User;

public class UserMapper {

    public static UserResponseDto toUserResponseDto(User user){

        UserResponseDto dto=new UserResponseDto();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUsername());
        dto.setRole(
                user.getRoles().stream().map((req)->
                        {
                            return req.getRoleName();
                        }
                ).toList().toString());

        return dto;
    }

}
