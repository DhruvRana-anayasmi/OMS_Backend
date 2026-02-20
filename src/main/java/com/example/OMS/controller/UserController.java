package com.example.OMS.controller;

import com.example.OMS.dto.*;
import com.example.OMS.entity.User;
import com.example.OMS.entity.principle.UserPrinciple;
import com.example.OMS.service.AuthService;
import com.example.OMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("users")
    public List<UserResponseDto> getUsers(){
        return service.getUsers();
    }

    @PostMapping("register")
    public RegisterResponseDto createUser(@RequestBody RegisterRequestDto user){
        return service.createUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto user) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        UserPrinciple principal = (UserPrinciple) auth.getPrincipal();
        assert principal != null;
            String token = authService.generateToken(principal);
            return ResponseEntity.ok(token);

    }

    @GetMapping("/me")
    public UserProfileDto getCurrentUser(Authentication authentication) {

        UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();

        return new UserProfileDto(
                principal.getUserId(),
                principal.getUsername(),
                principal.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
        );
    }


    @DeleteMapping("user/{userId}")
    public String deleteUser(@PathVariable Long userId){
        return service.deleteUser(userId);
    }
}
