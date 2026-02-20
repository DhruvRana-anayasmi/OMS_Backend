package com.example.OMS.controller;

import com.example.OMS.entity.Role;
import com.example.OMS.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class RoleController {

    @Autowired
    RoleService service;

    @PostMapping("role")
    public Role createRole(@RequestBody Role role){
        return service.createRole(role);
    }
}
