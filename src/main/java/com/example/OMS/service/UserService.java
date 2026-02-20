package com.example.OMS.service;

import com.example.OMS.dto.RegisterRequestDto;
import com.example.OMS.dto.RegisterResponseDto;
import com.example.OMS.dto.UserResponseDto;
import com.example.OMS.entity.Role;
import com.example.OMS.entity.User;
import com.example.OMS.entity.User_Role;
import com.example.OMS.exception.UserNotFoundException;
import com.example.OMS.mapper.UserMapper;
import com.example.OMS.repository.UserRepository;
import com.example.OMS.repository.User_RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    User_RoleRepository user_role_repo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public RegisterResponseDto createUser(RegisterRequestDto request) {

        User user=new User();
        User_Role userRole=new User_Role();


                user.setUsername(request.getUsername());
                user.setPassword(encoder.encode(request.getPassword()));
                user.setStatus("ACTIVE");
                user.setCreatedBy(0L);
                user.setUpdatedBy(0L);
                user.setUpdatedAt(LocalDateTime.now());
                User savedUser=repo.save(user);
                userRole.setUserId(savedUser.getUserId());
                userRole.setRoleId(2);


                user_role_repo.save(userRole);

            return new RegisterResponseDto(savedUser.getUsername(), savedUser.getUserId());

    }

    public List<UserResponseDto> getUsers() {
        return repo.findAll().stream().map(UserMapper::toUserResponseDto).toList();
    }

    public String deleteUser(Long userId) {
        User user=repo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        user.setStatus("INACTIVE");
        return "User deactivated";
    }
}
